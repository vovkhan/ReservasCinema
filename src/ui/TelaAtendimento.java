package ui;

import model.Sessao;
import model.Sala;

public class TelaAtendimento {

    public static void mostrarSala(Sessao sessao) {
        Sala sala = sessao.getSala();
        boolean[][] poltronas = sala.getPoltronas();

        System.out.println("\n=================================================");
        System.out.println("            T E L A - " + sessao.getFilme().getTitulo());
        System.out.println("=================================================\n");

        //Números no topo de exibição de colunas(mapeamento)
        System.out.print("    ");
        for (int c = 0; c < poltronas[0].length; c++) {
            System.out.printf(" %2d ", c);
        }
        System.out.println("\n");

        // Letras na lateral (Linhas)
        for (int i = 0; i < poltronas.length; i++) {
            char letra = (char) ('A' + i);
            System.out.printf("  %c   ", letra);

            for (int j = 0; j < poltronas[i].length; j++) {
                if (poltronas[i][j]) {
                    System.out.print("[X]");
                } else {
                    System.out.print("[L]");
                }
            }
            System.out.println();
        }
        
        //apenas uma legenda pra exibir o que significa cada simbolo do mapa de poltronas.
        System.out.println("\nL = Livre | X = Ocupado\n");
    }
}