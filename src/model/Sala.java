package model;

public class Sala {

    private int linhas;
    private int colunas;
    private boolean[][] poltronas;

    public Sala(int linhas, int colunas) {
        this.linhas = linhas;
        this.colunas = colunas;
        this.poltronas = new boolean[linhas][colunas];
    }

    public int getLinhas() {
        return linhas;
    }

    public int getColunas() {
        return colunas;
    }

    public boolean[][] getPoltronas() {
        return poltronas;
    }

    // Reserva uma poltrona (ex: A1, B3)
    public boolean reservarPoltrona(String assento) {

        int coluna = assento.toUpperCase().charAt(0) - 'A';
        int linha = Integer.parseInt(assento.substring(1)) - 1;

        if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {

            if (!poltronas[linha][coluna]) {
                poltronas[linha][coluna] = true;
                return true;
            }
        }

        return false;
    }

    // Cancela uma poltrona
    public boolean cancelarPoltrona(String assento) {

        int coluna = assento.toUpperCase().charAt(0) - 'A';
        int linha = Integer.parseInt(assento.substring(1)) - 1;

        if (linha >= 0 && linha < linhas && coluna >= 0 && coluna < colunas) {

            if (poltronas[linha][coluna]) {
                poltronas[linha][coluna] = false;
                return true;
            }
        }

        return false;
    }

    // Verifica se a sala está lotada
    public boolean estaLotada() {

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {

                if (!poltronas[i][j]) {
                    return false;
                }

            }
        }

        return true;
    }

    // Conta quantos assentos estão ocupados
    public int contarOcupados() {

        int total = 0;

        for (int i = 0; i < linhas; i++) {
            for (int j = 0; j < colunas; j++) {

                if (poltronas[i][j]) {
                    total++;
                }

            }
        }

        return total;
    }

    // Conta quantos assentos ainda estão livres
    public int contarLivres() {
        return (linhas * colunas) - contarOcupados();
    }
}