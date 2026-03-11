package model;
import java.io.Serializable;


public class IngressoInteira extends Ingresso implements Serializable {

    public IngressoInteira(String poltrona, double precoBase) {
        super(poltrona, precoBase);
    }

    @Override
    public double calcularPreco() {
        return precoBase;
    }
}