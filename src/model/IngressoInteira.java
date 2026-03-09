package model;

public class IngressoInteira extends Ingresso {

    public IngressoInteira(String poltrona, double precoBase) {
        super(poltrona, precoBase);
    }

    @Override
    public double calcularPreco() {
        return precoBase;
    }
}