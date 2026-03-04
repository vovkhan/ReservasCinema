package controladores;
import modelos.Cliente;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class ControladorCliente {

    private List<Cliente> bancoClientes;
    public ControladorCliente(){
        this.bancoClientes = new ArrayList<>();


    }
    
    public Cliente cadastrarCliente(String cpf, String nome, LocalDate dataNascimento){
        if(buscarClienteCpf(cpf)!= null){
            throw new IllegalArgumentException("Erro: O Cpf ja esta cadastrado!");
        }

        Cliente cliente = new Cliente(cpf, nome, dataNascimento);
        bancoClientes.add(cliente);
        System.out.println("Cliente " + nome + " cadastrado com exito!")
        
        return cliente;
    }

}
