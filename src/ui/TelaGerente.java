package ui;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import model.Filme;
import model.Sala;
import model.Sessao;
import service.CinemaService;

/*  
Tela para o gerente. 
- ele pode criar novas sessões
- listar as sessões existentes
- remover sessões e gerar relatórios do cinema.
O gerente deve ser capaz de inserir os detalhes da sessão, como o filme, horário e sala, e o sistema deve validar as informações antes de criar a sessão.
*/
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
            System.out.println("1 - Criar sessao");
            System.out.println("2 - Listar sessoes");
            System.out.println("3 - Remover sessao");
            System.out.println("4 - Relatório do cinema");
            System.out.println("0 - Voltar");
            System.out.print("Escolha: ");
            opcao = sc.nextInt();
            sc.nextLine(); 

            switch (opcao) {
                case 1: criarSessao(); break;
                case 2: System.out.println(service.mostrarSessoes()); break; // CORREÇÃO
                case 3: removerSessao(); break;
                case 4: System.out.println(service.gerarRelatorio()); break; // CORREÇÃO
                case 0: System.out.println("Voltando..."); break;
                default: System.out.println("Opçao inválida.");
            }
        } while (opcao != 0);
    }


    // Logica de criação de sessão.
    private void criarSessao() {
        
        System.out.print("Nome do filme: ");
        String nomeFilme = sc.nextLine();
        
        Filme filme = new Filme(nomeFilme, "Não definido", 120, "Livre"); 

        System.out.print("Horario (dd/MM/yyyy HH:mm): ");
        String horarioStr = sc.nextLine();

        
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        LocalDateTime horario = LocalDateTime.parse(horarioStr, fmt);

        System.out.print("Linhas da sala: ");
        int linhas = sc.nextInt();
        System.out.print("Colunas da sala: ");
        int colunas = sc.nextInt();

        Sala sala = new Sala(linhas, colunas);
        Sessao sessao = new Sessao(filme, horario, sala);
        service.adicionarSessao(sessao);
        System.out.println("Sessao criada com sucesso.");
    }

    private void removerSessao() {
        System.out.print("Digite o ID da sessao: ");
        int id = sc.nextInt();
        if (service.removerSessao(id)) {
            System.out.println("Sessao removida.");
        } else {
            System.out.println("Sessao não encontrada.");
        }
    }
}