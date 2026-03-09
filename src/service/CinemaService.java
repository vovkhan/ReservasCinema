package service;

import java.util.ArrayList;
import java.util.List;

import model.Ingresso;
import model.Sessao;
import exception.SessaoNaoEncontradaException;

public class CinemaService {

    private List<Sessao> sessoes;

    public CinemaService() {
        sessoes = new ArrayList<>();
    }

    public void adicionarSessao(Sessao sessao) {
        sessoes.add(sessao);
    }

    public List<Sessao> listarSessoes() {
        return sessoes;
    }

    public Sessao buscarSessao(int id) throws SessaoNaoEncontradaException {

        for (Sessao s : sessoes) {

            if (s.getId() == id) {
                return s;
            }
        }

        throw new SessaoNaoEncontradaException();
    }

    public void comprarIngresso(int idSessao, Ingresso ingresso)
            throws SessaoNaoEncontradaException {

        Sessao sessao = buscarSessao(idSessao);

        sessao.venderIngresso(ingresso);
    }

    public void cancelarIngresso(int idSessao, String poltrona)
            throws SessaoNaoEncontradaException {

        Sessao sessao = buscarSessao(idSessao);

        sessao.cancelarIngresso(poltrona);
    }

    public void mostrarSessoes() {

        for (Sessao s : sessoes) {

            System.out.println(
                    "ID: " + s.getId() +
                            " | Filme: " + s.getFilme() +
                            " | Horário: " + s.getHorario()
            );
        }
    }
    public boolean removerSessao(int id) {

        for (int i = 0; i < sessoes.size(); i++) {

            if (sessoes.get(i).getId() == id) {

                sessoes.remove(i);
                return true;
            }
        }

        return false;
    }

    public void relatorio() {

        System.out.println("\n===== RELATÓRIO DO CINEMA =====");

        for (Sessao s : sessoes) {

            System.out.println("\nFilme: " + s.getFilme());
            System.out.println("Horário: " + s.getHorario());
            System.out.println("Ingressos vendidos: " + s.ingressosVendidos());
            System.out.println("Faturamento: R$ " + s.faturamento());
        }
    }
}