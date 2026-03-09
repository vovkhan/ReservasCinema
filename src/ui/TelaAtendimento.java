package ui;

import model.Sessao;
import model.Sala;

public class TelaAtendimento {

    public static void mostrarSala(Sessao sessao) {

        Sala sala = sessao.getSala();
        boolean[][] poltronas = sala.getPoltronas();

        System.out.println("\n=================================================");
        System.out.println("                    T E L A");
        System.out.println("=================================================\n");

        // Letras das colunas
        System.out.print("     ");
        for (char c = 'A'; c < 'A' + poltronas[0].length; c++) {
            System.out.printf("  %c ", c);
        }
        System.out.println("\n");

        // Linhas
        for (int i = 0; i < poltronas.length; i++) {

            System.out.printf(" %2d   ", i + 1);

            for (int j = 0; j < poltronas[i].length; j++) {

                if (poltronas[i][j]) {
                    System.out.print("[🔴]");
                } else {
                    System.out.print("[🟢]");
                }
            }

            System.out.println();
        }

        System.out.println();
        System.out.println("🟢 = Livre");
        System.out.println("🔴 = Ocupado\n");
    }
}