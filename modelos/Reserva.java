package modelos; // Dica: por padrão em Java, pacotes costumam ser no singular (model), mas modelos tá valendo!

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Reserva {

    private String idTicket;
    private Sessao sessao;
    private Cliente cliente; // Pode ser null se não for cadastrado
    private List<String> assentosReservados;
    private LocalDateTime dataEmissao;
    private double valorTotal;

    // O valorTotal agora é RECEBIDO, e não calculado aqui dentro
    public Reserva(Sessao sessao, Cliente cliente, List<String> assentosReservados, double valorTotalCalculado) {
        // Usando substring para o ID do ticket não ficar um texto gigante no bilhete
        this.idTicket = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); 
        this.sessao = sessao;
        this.cliente = cliente;
        this.assentosReservados = assentosReservados;
        this.dataEmissao = LocalDateTime.now();
        this.valorTotal = valorTotalCalculado; 
    }

    // Getters
    public String getIdTicket() { return idTicket; }
    public Sessao getSessao() { return sessao; }
    public Cliente getCliente() { return cliente; }
    public List<String> getAssentosReservados() { return assentosReservados; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public double getValorTotal() { return valorTotal; }
}