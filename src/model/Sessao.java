package model;

import java.util.ArrayList;
import java.util.List;

public class Sessao {

    private int id;
    private String filme;
    private String horario;
    private Sala sala;

    private List<Ingresso> ingressos;

    public Sessao(int id, String filme, String horario, Sala sala) {

        this.id = id;
        this.filme = filme;
        this.horario = horario;
        this.sala = sala;

        this.ingressos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFilme() {
        return filme;
    }

    public String getHorario() {
        return horario;
    }

    public Sala getSala() {
        return sala;
    }

    public List<Ingresso> getIngressos() {
        return ingressos;
    }

    public boolean poltronaOcupada(String poltrona) {

        for (Ingresso i : ingressos) {
            if (i.getPoltrona().equalsIgnoreCase(poltrona)) {
                return true;
            }
        }

        return false;
    }

    public void venderIngresso(Ingresso ingresso) {

        if (poltronaOcupada(ingresso.getPoltrona())) {

            System.out.println("Poltrona já ocupada.");
            return;
        }

        sala.reservarPoltrona(ingresso.getPoltrona());

        ingressos.add(ingresso);

        System.out.println("Ingresso vendido. Valor: R$ " + ingresso.calcularPreco());
    }

    public void cancelarIngresso(String poltrona) {

        Ingresso remover = null;

        for (Ingresso i : ingressos) {

            if (i.getPoltrona().equalsIgnoreCase(poltrona)) {
                remover = i;
                break;
            }
        }

        if (remover != null) {

            sala.cancelarPoltrona(poltrona);

            ingressos.remove(remover);

            System.out.println("Reserva cancelada.");

        } else {

            System.out.println("Poltrona não encontrada.");
        }
    }

    public int ingressosVendidos() {
        return ingressos.size();
    }

    public double faturamento() {

        double total = 0;

        for (Ingresso i : ingressos) {
            total += i.calcularPreco();
        }

        return total;
    }
}