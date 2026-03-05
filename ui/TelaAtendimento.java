package ui;
import java.util.Scanner;
import controladores.ControladorCliente;
import modelos.Sessao;
import modelos.Sala;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TelaAtendimento {
    private Scanner scanner;
    private ControladorCliente controladorCliente;

    public TelaAtendimento(ControladorCliente controladorCliente){
        
        this.controladorCliente = controladorCliente;
        this.scanner= new Scanner(System.in);


    }

    public void iniciarAtendimento(Sessao sessaoAtual){
        int opcao= 0;


        while (opcao != 3){
            
            System.out.println("Bem-vindo ao Cinema! Por favor, escolha a opção que deseja realizar:");
            System.out.println("1. Realizar reserva");
            System.out.println("2. Consultar reservas");
            System.out.println("3. Consultar histórico de reservas");
            System.out.println("4. Sair");
            
            opcao = scanner.nextInt();
            scanner.nextLine();


            switch(opcao){
                case 1:
                    menuCadastarCliente();
                    break;

                case 2:
                    menuConsultarReservas();
                    break;

                case 3:
                    exibirLugares(sessaoAtual);
                    break;

                case 4:
                    System.out.println("saindo do painel");
                    break;
                default:
                    System.out.println("Opção invalida. Tente outra opção por favor.");

            }


        }
    }

    private void menuCadastarCliente(){
        System.out.println("\nRealizando novo cadastro de cliente...");
        System.out.println("Informe o CPF do cliente - ");

        String cpf = scanner.nextLine();

        System.out.println("Informe o nome do cliente - ");

        String nome = scanner.nextLine();

        System.out.println("Informe a data de nascimento do cliente no seginte fomato: (Dia/Mês/ano - " );

        String dataStr = scanner.nextLine();

        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dataNascimento = LocalDate.parse(dataStr, formatter);
            controladorCliente.cadastrarCliente(cpf, nome, dataNascimento);
        }catch(Exception e){
            System.out.println("Erro. Verifique se a data foi inserida no formato correto e tente novamente.");
        }

        }
    }


    
}
