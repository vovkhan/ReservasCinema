import service.CinemaService;
import ui.Atendimento;
import ui.TelaGerente;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        
        // Instanciando o "Cérebro" do sistema (que guarda as listas de tudo)
        CinemaService service = new CinemaService();
        
        popularBancoDeDadosIncial(service); //sistema com dados iniciais (3 filmes, 3 salas, 3 sessões)
        
        // Instanciando as telas e passando o mesmo 'service' para ambas
        // Isso garante que o Atendente veja as sessões que o Gerente criar
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
            System.out.println("1 - Entrar como Gerente (Administraçao)");
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
        
    }
}