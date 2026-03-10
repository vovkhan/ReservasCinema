import service.CinemaService;
import ui.Atendimento;
import ui.TelaGerente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // 1. Instanciando o "Cérebro" do sistema (que guarda as listas de tudo)
        CinemaService service = new CinemaService();

        popularBancoDeDadosIncial(service); //sistema com dados iniciais (3 filmes, 3 salas, 3 sessões)
        
        // 2. Instanciando as telas e passando o mesmo 'service' para ambas
        // Isso garante que o Atendente veja as sessões que o Gerente criar!
        TelaGerente telaGerente = new TelaGerente(service);
        Atendimento telaAtendimento = new Atendimento(service);
        
        Scanner sc = new Scanner(System.in);
        int opcao = -1;

        System.out.println("=========================================");
        System.out.println("      BEM-VINDO AO CINEMA!!         ");
        System.out.println("=========================================");

        // 3. O Loop de Login
        do {
            System.out.println("\n===== TELA DE ACESSO =====");
            System.out.println("1 - Entrar como Gerente (Administração)");
            System.out.println("2 - Entrar como Atendente (Vendas)");
            System.out.println("0 - Desligar Sistema");
            System.out.print("Digite o seu perfil de acesso: ");
            
            try {
                opcao = sc.nextInt();

                switch (opcao) {
                    case 1:
                        telaGerente.iniciar(); // Chama o loop do Gerente
                        break;
                    case 2:
                        telaAtendimento.iniciar(); // Chama o loop do Atendente
                        break;
                    case 0:
                        System.out.println("Desligando o sistema do cinema... Até mais!");
                        break;
                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro: Entrada inválida. Digite apenas números.");
                sc.nextLine(); // Limpa o buffer para não dar loop infinito de erro
            }

        } while (opcao != 0);

        sc.close();
    }

    private static void popularBancoDeDadosIncial(CinemaService service) {
        // 1. Criando os 3 Filmes
        model.Filme f1 = new model.Filme("O cavaleiro das trevas", "Ação", 175, "12 anos");
        model.Filme f2 = new model.Filme("Duna: Parte 2", "Ficção Científica", 166, "12 anos");
        model.Filme f3 = new model.Filme("Vingadores o Ultimato", "Ação", 180, "12 anos");

        // 2. Criando as 3 Salas (com tamanhos diferentes para ficar legal)
        model.Sala s1 = new model.Sala(10, 7); 
        model.Sala s2 = new model.Sala(10, 7); 
        model.Sala s3 = new model.Sala(10, 7); 

        // 3. Criando os 3 Horários (pra hoje e pra amanhã)
        java.time.LocalDateTime h1 = java.time.LocalDateTime.now().plusHours(14);
        java.time.LocalDateTime h2 = java.time.LocalDateTime.now().plusHours(14);
        java.time.LocalDateTime h3 = java.time.LocalDateTime.now().plusDays(3).plusHours(19);

        // 4. Agendando as Sessões no Service
        service.adicionarSessao(new model.Sessao(1, f1, h1, s1));
        service.adicionarSessao(new model.Sessao(2, f2, h2, s2));
        service.adicionarSessao(new model.Sessao(3, f3, h3, s3));
    }
}