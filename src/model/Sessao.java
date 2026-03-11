package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;


public class Sessao implements Serializable {

    private static int contador = 1; // Contador para gerar IDs únicos
    private int id;
    private Filme filme; // CORREÇÃO: Voltou a ser o objeto Filme
    private LocalDateTime horario; // CORREÇÃO: Voltou a ser LocalDateTime
    private Sala sala;
    private List<Ingresso> ingressos;

    public Sessao(Filme filme, LocalDateTime horario, Sala sala) {
        
        this.id = contador++;
        this.filme = filme;
        this.horario = horario;
        this.sala = sala;
        this.ingressos = new ArrayList<>();
    }

    public int getId() { return id; }
    public Filme getFilme() { return filme; }
    public LocalDateTime getHorario() { return horario; }
    public Sala getSala() { return sala; }
    public List<Ingresso> getIngressos() { return ingressos; }

    public boolean poltronaOcupada(String poltrona) {
        for (Ingresso i : ingressos) {
            if (i.getPoltrona().equalsIgnoreCase(poltrona)) {
                return true;
            }
        }
        return false;
    }

    // CORREÇÃO MVC: Removido os prints. Agora ele lança erro ou funciona calado.
    public void venderIngresso(Ingresso ingresso) {
        if (poltronaOcupada(ingresso.getPoltrona())) {
            throw new IllegalArgumentException("Erro: Poltrona já ocupada.");
        }
        sala.reservarPoltrona(ingresso.getPoltrona());
        ingressos.add(ingresso);
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
        } else {
            throw new IllegalArgumentException("Erro: Poltrona não encontrada.");
        }
    }

    // Pode colar isso no final da classe Sessao.java

    public int ingressosVendidos() {
        return ingressos.size(); // Retorna o tamanho da lista de ingressos vendidos
    }

    public double faturamento() {
        double total = 0;
        for (Ingresso i : ingressos) {
            total += i.calcularPreco(); // Soma o valor usando o Polimorfismo (Meia ou Inteira)
        }
        return total;
    }
}