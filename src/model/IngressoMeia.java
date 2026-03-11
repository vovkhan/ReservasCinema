package model;
import java.io.Serializable;


public class IngressoMeia extends Ingresso implements Serializable {

    public IngressoMeia(String poltrona, double precoBase) {
        super(poltrona, precoBase);
    }

    @Override
    public double calcularPreco() {
        return precoBase / 2;
    }
}