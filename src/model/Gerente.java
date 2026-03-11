package model;
import java.io.Serializable;


public class Gerente extends Usuario implements Serializable {
    
    public Gerente(String cpf, String nome, String senha) {
        super(cpf, nome, senha);
    }
    
}
