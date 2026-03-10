package ui;

import java.util.Scanner;
import java.time.format.DateTimeFormatter;
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
            System.out.println("5 - Faturamento da sessão");
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
        } while (opcao != 0);
    }

    private void comprarIngresso() {
        System.out.println(service.mostrarSessoes());

        System.out.print("\nDigite o ID da sessão (ou 0 para cancelar): ");
        int id = sc.nextInt();
        sc.nextLine(); // FAXINA 1: Limpa o "Enter" que ficou solto no teclado

        if (id == 0) return; 

        try {
            Sessao sessao = service.buscarSessao(id);
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

            System.out.println("\n--- INICIANDO VENDA ---");
            System.out.println("Filme: " + sessao.getFilme().getTitulo());
            System.out.println("Horário: " + sessao.getHorario().format(fmt));

            ui.TelaAtendimento.mostrarSala(sessao);

            boolean poltronaValida = false;
            String poltrona = "";

            while (!poltronaValida) {
                // Deixando claro pro atendente que é uma por vez
                System.out.print("Escolha APENAS UMA poltrona (ex: A0): ");
                
                // FAXINA 2: Lê a linha inteira, tira os espaços em branco e já põe maiúsculo
                poltrona = sc.nextLine().trim().toUpperCase(); 

                // Se o cara digitar "A3, A4", a gente corta e pega só os dois primeiros caracteres "A3"
                if (poltrona.length() > 2) {
                    poltrona = poltrona.substring(0, 2); 
                }

                if (sessao.poltronaOcupada(poltrona)) {
                    System.out.println("(X) Ops! Essa poltrona já está ocupada. Tente outra.");
                } else {
                    poltronaValida = true; 
                }
            }

            System.out.println("\nTipo de ingresso:");
            System.out.println("1 - Inteira (R$ 30,00)");
            System.out.println("2 - Meia (R$ 15,00)");
            System.out.print("Escolha: ");
            int tipo = sc.nextInt();
            sc.nextLine(); // FAXINA 3: Limpa o "Enter" de novo!

            Ingresso ingresso = (tipo == 1) ? new IngressoInteira(poltrona, 30) : new IngressoMeia(poltrona, 30);

            // O back-end processa a venda e muda o status da matriz para ocupado
            service.comprarIngresso(id, ingresso);
            System.out.println("✅ Venda confirmada com sucesso! O assento " + poltrona + " foi reservado.");

            // 👉 ATUALIZAÇÃO DO MAPA NA TELA:
            System.out.println("\n--- STATUS ATUALIZADO DO MAPA ---");
            ui.TelaAtendimento.mostrarSala(sessao);

        } catch (java.util.InputMismatchException e) {
            System.out.println("Erro: Você digitou texto onde deveria ser um número.");
            sc.nextLine(); // Se ele digitar letra no lugar do ID, a gente limpa o lixo pra não dar loop infinito
        } catch (Exception e) {
            System.out.println("Erro durante a venda: " + e.getMessage());
        }
    }

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