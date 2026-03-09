package main;

import java.util.Scanner;

import model.Sessao;
import service.CinemaService;
import ui.TelaAtendimento;
import ui.TelaGerente;
import exception.SessaoNaoEncontradaException;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CinemaService service = new CinemaService();
        TelaGerente gerente = new TelaGerente(service);

        int opcao;

        do {

            System.out.println("\n===============================");
            System.out.println("        SISTEMA CINEMA");
            System.out.println("===============================");
            System.out.println("1 - Atendimento");
            System.out.println("2 - Gerente");
            System.out.println("0 - Sair");
            System.out.print("Escolha: ");

            opcao = sc.nextInt();

            switch (opcao) {

                case 1:

                    if (service.listarSessoes().isEmpty()) {
                        System.out.println("Nenhuma sessão cadastrada.");
                        break;
                    }

                    service.mostrarSessoes();

                    System.out.print("Digite o ID da sessão: ");
                    int id = sc.nextInt();

                    try {

                        Sessao sessao = service.buscarSessao(id);

                        TelaAtendimento.mostrarSala(sessao);

                    } catch (SessaoNaoEncontradaException e) {

                        System.out.println("Sessão não encontrada.");
                    }

                    break;

                case 2:

                    gerente.iniciar();
                    break;

                case 0:

                    System.out.println("Encerrando sistema...");
                    break;

                default:

                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        sc.close();
    }
}