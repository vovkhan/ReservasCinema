package ui;

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
            System.out.println("5 - Faturamento da sessão");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    comprarIngresso();
                    break;

                case 2:
                    cancelarIngresso();
                    break;

                case 3:
                    mostrarSala();
                    break;

                case 4:
                    ingressosVendidos();
                    break;

                case 5:
                    faturamento();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void comprarIngresso() {

        service.mostrarSessoes();

        System.out.print("\nDigite o ID da sessão: ");
        int id = sc.nextInt();

        try {

            Sessao sessao = service.buscarSessao(id);

            System.out.println("\nFilme: " + sessao.getFilme());
            System.out.println("Horário: " + sessao.getHorario());

            TelaAtendimento.mostrarSala(sessao);

            System.out.print("Escolha a poltrona (ex: A1): ");
            String poltrona = sc.next();

            System.out.println("Tipo de ingresso:");
            System.out.println("1 - Inteira");
            System.out.println("2 - Meia");

            int tipo = sc.nextInt();

            Ingresso ingresso;

            if (tipo == 1) {
                ingresso = new IngressoInteira(poltrona, 30);
            } else {
                ingresso = new IngressoMeia(poltrona, 30);
            }

            service.comprarIngresso(id, ingresso);

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void cancelarIngresso() {

        System.out.print("ID da sessão: ");
        int id = sc.nextInt();

        System.out.print("Poltrona: ");
        String poltrona = sc.next();

        try {

            service.cancelarIngresso(id, poltrona);

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }

    private void mostrarSala() {

        service.mostrarSessoes();

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

            System.out.println("Faturamento: R$ " + sessao.faturamento());

        } catch (Exception e) {

            System.out.println("Erro: " + e.getMessage());
        }
    }
}