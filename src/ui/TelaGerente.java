package ui;

import java.util.Scanner;

import model.Sala;
import model.Sessao;
import service.CinemaService;

public class TelaGerente {

    private CinemaService service;
    private Scanner sc;

    public TelaGerente(CinemaService service) {
        this.service = service;
        this.sc = new Scanner(System.in);
    }

    public void iniciar() {

        int opcao;

        do {

            System.out.println("\n===== GERENTE =====");
            System.out.println("1 - Criar sessão");
            System.out.println("2 - Listar sessões");
            System.out.println("3 - Remover sessão");
            System.out.println("4 - Relatório do cinema");
            System.out.println("0 - Voltar");

            System.out.print("Escolha: ");
            opcao = sc.nextInt();

            switch (opcao) {

                case 1:
                    criarSessao();
                    break;

                case 2:
                    service.mostrarSessoes();
                    break;

                case 3:
                    removerSessao();
                    break;

                    case 4:
                    service.relatorio();
                    break;

                case 0:
                    System.out.println("Voltando...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);
    }

    private void criarSessao() {

        System.out.print("ID da sessão: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Nome do filme: ");
        String filme = sc.nextLine();

        System.out.print("Horário: ");
        String horario = sc.nextLine();

        System.out.print("Linhas da sala: ");
        int linhas = sc.nextInt();

        System.out.print("Colunas da sala: ");
        int colunas = sc.nextInt();

        Sala sala = new Sala(linhas, colunas);

        Sessao sessao = new Sessao(id, filme, horario, sala);

        service.adicionarSessao(sessao);

        System.out.println("Sessão criada com sucesso.");
    }

    private void removerSessao() {

        System.out.print("Digite o ID da sessão: ");
        int id = sc.nextInt();

        if (service.removerSessao(id)) {
            System.out.println("Sessão removida.");
        } else {
            System.out.println("Sessão não encontrada.");
        }
    }
}