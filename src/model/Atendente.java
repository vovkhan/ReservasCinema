package model;
import java.io.Serializable;

public class Atendente extends Usuario implements Serializable {

    public Atendente(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }
    
}
