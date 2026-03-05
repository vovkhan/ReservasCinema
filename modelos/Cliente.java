package modelos;
import java.time.LocalDate;
import java.time.Period;


public class Cliente {
    private String cpf;
    private String nome;
    private LocalDate nascimento;
    private int ingressosAdquiridos;



    public Cliente(String cpf, String nome, LocalDate nascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.nascimento = nascimento;
        this.ingressosAdquiridos = 0;
    }

    public int getIdade() {
        LocalDate hoje = LocalDate.now();
        return Period.between(nascimento, hoje).getYears();
    }
    public void adicionarCompra(int quantidade){
        this.ingressosAdquiridos += quantidade;
    }

    //getters

    public String getCpf() {
        return cpf;
    }

    public String getnome(){
        return nome;
    }

    public LocalDate getNascimento() {
        return nascimento;
    }

    public int getIngressosAdquiridos() {
        return ingressosAdquiridos;
    }
    
}
