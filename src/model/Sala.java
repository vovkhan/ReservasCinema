package model;
import java.io.Serializable;


public class Sala implements Serializable {

    private int linhas;
    private int colunas;
    private boolean[][] poltronas;

    public Sala(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.poltronas = new boolean[linhas][colunas];
    }

    public int getLinhas() { 
        return linhas; }
    public int getColunas() { 
        return colunas; }
    public boolean[][] getPoltronas() { 
        return poltronas; }

    // Reserva uma poltrona (ex: A0, B3) - Corrigindo Letra=Linha, Numero=Coluna
    public boolean reservarPoltrona(String assento) {
        int linha = assento.toUpperCase().charAt(0) - 'A';
        int coluna = Integer.parseInt(assento.substring(1));

        if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {
            if (!poltronas[linha][coluna]) {
                poltronas[linha][coluna] = true;
                return true;
            }
        }
        return false;
    }

    public boolean cancelarPoltrona(String assento) {
        int linha = assento.toUpperCase().charAt(0) - 'A';
        int coluna = Integer.parseInt(assento.substring(1));

        if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {
            if (poltronas[linha][coluna]) {
                poltronas[linha][coluna] = false;
                return true;
            }
        }
        return false;
    }

    // Método que a sua TelaAtendimento usa
    public boolean isLugarVago(int linha, int coluna) {
        return !poltronas[linha][coluna];
    }
}