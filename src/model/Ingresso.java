package model;

public abstract class Ingresso {

    protected String poltrona;
    protected double precoBase;

    public Ingresso(String poltrona, double precoBase) {
        this.poltrona = poltrona;
        this.precoBase = precoBase;
    }

    public String getPoltrona() {
        return poltrona;
    }

    public abstract double calcularPreco();
}