package model;

public class IngressoMeia extends Ingresso {

    public IngressoMeia(String poltrona, double precoBase) {
        super(poltrona, precoBase);
    }

    @Override
    public double calcularPreco() {
        return precoBase / 2;
    }
}