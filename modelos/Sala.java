package modelos;

public class Sala {
    
    private int numeroSala;

    private boolean [][] lugares;

    public Sala(int numeroSala, int nLinhas, int nColunas){
        this.numeroSala = numeroSala;
        this.lugares = new boolean[nLinhas][nColunas];
    }

    public int getNumeroSala(){
        return numeroSala;

    }

    public boolean isLugarVago(int linha, int coluna){
        return !lugares[linha][coluna];
    }

    public void reservarLugar(int linha, int coluna){
        this.lugares[linha][coluna] = true;
    }

    public void liberarlugar(int linha, int coluna){
        this.lugares[linha][coluna] = false;
    }

    


}
