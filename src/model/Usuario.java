package model;
import java.io.Serializable;



public abstract class Usuario implements Serializable {
    protected String matricula;
    protected String senha;
    protected String nome;

    public Usuario(String matricula, String senha, String nome) {
        this.matricula = matricula;
        this.senha = senha;
        this.nome = nome;
    }

    public String getMatricula(){
        return matricula;

    }

    public String getSenha(){
        return senha;
    }

    public String getNome(){
        return nome;
    }

}
