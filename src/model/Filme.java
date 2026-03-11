package model;
import java.io.Serializable;


public class Filme implements Serializable {

    private String titulo;
    private String genero;
    private int duracao;
    private String faixaEtariaindicada;

    public Filme(String titulo, String genero, int duracao, String faixaEtariaindicada) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracao = duracao;
        this.faixaEtariaindicada = faixaEtariaindicada;
    }

    //getters
    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracao() {
        return duracao;
    }


    public String getFaixaEtariaindicada() {
        return faixaEtariaindicada;
    }

    //setters

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public void setFaixaEtariaindicada(String faixaEtariaindicada) {
        this.faixaEtariaindicada = faixaEtariaindicada;
    }


}
