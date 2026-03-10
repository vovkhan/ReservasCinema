package ui;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import model.Ingresso;
import model.IngressoInteira;
import model.IngressoMeia;
import model.Sessao;
import service.CinemaService;

public class Atendimento {
    private CinemaService service;
    private Scanner sc;

    public Atendimento(CinemaService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {
        int opcao;
        do {
            System.out.println("\n===== ATENDIMENTO =====");
            System.out.println("1 - Comprar ingresso");
            System.out.println("2 - Cancelar ingresso");
            System.out.println("3 - Mostrar sala");
            System.out.println("4 - Ingressos vendidos");
            System.out.println("5 - Faturamento da sessao");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {
                case 1: comprarIngresso(); break;
                case 2: cancelarIngresso(); break;
                case 3: mostrarSala(); break;
                case 4: ingressosVendidos(); break;
                case 5: faturamento(); break;
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opção inválida.");
            }
        } 
        while (opcao != 0);
    }



    // Lógica para comprar ingresso, onde o atendente pode escolher a sessão, a quantidade de ingressos, as poltronas e o tipo de ingresso (inteira ou meia). O sistema irá validar se as poltronas estão disponíveis e processar a venda, atualizando o mapa de poltronas da sessão em tempo real.
    private void comprarIngresso() {
        
        System.out.println(service.mostrarSessoes());
        System.out.print("\nDigite o ID da sessão (ou 0 para cancelar): ");
        int id = sc.nextInt();
        sc.nextLine(); 

        if (id == 0) return; 

        try {
            Sessao sessao = service.buscarSessao(id);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            System.out.println("\n--- INICIANDO VENDA ---");
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario().format(fmt));

            ui.TelaAtendimento.mostrarSala(sessao);

            System.out.println();
            
            // MULTIPLOS INGRESSOS
            System.out.print("Quantos ingressos deseja comprar para essa sessao? ");
            int quantidade = sc.nextInt();
            sc.nextLine();

            if (quantidade <= 0) {
                System.out.println("Quantidade inválida. Voltando ao menu...");
                return;
            }

            // Repetição que ira rodar em decorrencia da quantidade de ingressos que o cliente deseja comprar
            for (int i = 1; i <= quantidade; i++) {
                
                System.out.println("\n--- Configurando Ingresso " + i + " de " + quantidade + " ---");
                boolean poltronaValida = false;
                String poltrona = "";

                while (!poltronaValida) {

                    
                    System.out.print("Escolha APENAS UMA poltrona (ex: A0): ");
                    
                    /*  
                    - Lê a linha inteira, tira os espaços em branco
                    - Transforma a letra em maiúscula pra evitar erro de digitação tipo "a0" ou " A0 "
                    */
                    poltrona = sc.nextLine().trim().toUpperCase(); 

                    // Logica pra caso o atendente digite mais de 2 caracteres, pegando então os dois primeiros e ignorando o resto que foi selecionado, evitando assim erros de digitação como "A01" ou "A0 " ou " A0
                    if (poltrona.length() > 2) {
                        poltrona = poltrona.substring(0, 2); 
                    }

                    if (sessao.poltronaOcupada(poltrona)) {
                        System.out.println("(X) Ops! Essa poltrona já está ocupada. Tente outra.");
                    } 
                    else {
                        poltronaValida = true; 
                    }
                }

                System.out.println("\nTipo de ingresso:");
                System.out.println("1 - Inteira (R$ 30,00)");
                System.out.println("2 - Meia (R$ 15,00)");
                System.out.print("Escolha: ");
                int tipo = sc.nextInt();
                sc.nextLine(); 

                Ingresso ingresso = (tipo == 1) ? new IngressoInteira(poltrona, 30) : new IngressoMeia(poltrona, 30);

                // Etapa de processamento de venda do ingresso, e atualização do mapa de poltronas na tela a cada compra realizada.
                service.comprarIngresso(id, ingresso);
                System.out.println(" Venda confirmada com sucesso! O assento " + poltrona + " foi reservado.");
            }

            //Atualização do mapa
            System.out.println("\n--- VENDA MÚLTIPLA CONCLUÍDA! STATUS ATUALIZADO DO MAPA ---");
            ui.TelaAtendimento.mostrarSala(sessao);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Você digitou texto onde deveria ser um número.");
            sc.nextLine(); // Se ele digitar letra no lugar do ID, a gente limpa o lixo pra não dar loop infinito
        } catch (Exception e) {
            System.out.println("Erro durante a venda: " + e.getMessage());
        }
    }
    
    
    //logica de cancelamento do ingresso
    private void cancelarIngresso() {
        System.out.print("ID da sessão: ");
        int id = sc.nextInt();
        System.out.print("Poltrona: ");
        String poltrona = sc.next().toUpperCase();
        try {
            service.cancelarIngresso(id, poltrona);
            System.out.println(" Ingresso da poltrona " + poltrona + " cancelado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    //Logia de exibição do mapa de poltronas da sessão selecionada, mostrando quais estão ocupadas e quais estão livres. Esta parte em espeficico não sera possivel fazer compra do ingresso, é um mecanismo de checagem apenas
    private void mostrarSala() {
        System.out.println(service.mostrarSessoes());
        System.out.print("Digite o ID da sessão: ");
        int id = sc.nextInt();

        try {
            Sessao sessao = service.buscarSessao(id);
            TelaAtendimento.mostrarSala(sessao);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    // Lógica para mostrar quantos ingressos foram vendidos para a sessão selecionada, utilizando o método ingressosVendidos
    private void ingressosVendidos() {
        System.out.print("ID da sessão: ");
        int id = sc.nextInt();
        try {
            Sessao sessao = service.buscarSessao(id);
            System.out.println("Ingressos vendidos: " + sessao.ingressosVendidos());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }


    //Logica de faturamento com cada sessão separada. O método faturamento da classe Sessao irá calcular o valor total arrecadado com a venda de ingressos para aquela sessão específica, somando o valor de cada ingresso vendido.
    private void faturamento() {
        System.out.print("ID da sessão: ");
        int id = sc.nextInt();
        try {
            Sessao sessao = service.buscarSessao(id);
            System.out.printf("Faturamento: R$ %.2f\n", sessao.faturamento());
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}