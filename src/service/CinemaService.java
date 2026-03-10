package service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import model.Ingresso;
import model.Sessao;
import exception.SessaoNaoEncontradaException;

public class CinemaService implements IRelatorio {

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
        throw new SessaoNaoEncontradaException(); // A nossa exceção customizada brilhando aqui!
    }

    public void comprarIngresso(int idSessao, Ingresso ingresso) throws SessaoNaoEncontradaException {
        Sessao sessao = buscarSessao(idSessao);
        sessao.venderIngresso(ingresso);
    }

    public void cancelarIngresso(int idSessao, String poltrona) throws SessaoNaoEncontradaException {
        Sessao sessao = buscarSessao(idSessao);
        sessao.cancelarIngresso(poltrona);
    }

    // CORREÇÃO MVC: Retorna uma String pronta em vez de printar na tela.
    // CORREÇÃO DE TIPO: Puxa getTitulo() e formata a Data.
    public String mostrarSessoes() {
       if (sessoes.isEmpty()) {
            return "Nenhuma sessão disponível.";
        }
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        sb.append("\n==== Sessões Disponíveis ====\n");
        for (Sessao s : sessoes) {
            int capacidadeTotal = s.getSala().getLinhas() * s.getSala().getColunas();
            int vagasLivres = capacidadeTotal - s.ingressosVendidos();


            sb.append("ID: ").append(s.getId()).append("| Filme: ").append(s.getFilme().getTitulo())
              .append(" | Horário: ").append(s.getHorario().format(fmt))
              .append(" | Vagas:  ").append(vagasLivres).append("\n");

            
        }
        return sb.toString();
    }

    // CORREÇÃO: Usando o removeIf (Muito mais elegante e rápido do que o for que estava antes)
    public boolean removerSessao(int id) {
        return sessoes.removeIf(s -> s.getId() == id);
    }

    // CORREÇÃO MVC: Mesmo esquema, constrói o relatório e devolve pra View printar.
    public String gerarRelatorio() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        sb.append("\n===== RELATÓRIO DO CINEMA =====\n");

        for (Sessao s : sessoes) {
            sb.append("\nFilme: ").append(s.getFilme().getTitulo());
            sb.append("\nHorário: ").append(s.getHorario().format(fmt));
            sb.append("\nIngressos vendidos: ").append(s.ingressosVendidos());
            sb.append(String.format("\nFaturamento: R$ %.2f\n", s.faturamento()));
        }
        return sb.toString();
    }
}