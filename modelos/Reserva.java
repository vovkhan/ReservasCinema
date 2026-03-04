package modelos;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Reserva {

    private String idTicket;
    private Sessao sessao;
    private Cliente cliente; // null se não for cadastrado
    private List<String> assentosReservados;
    private LocalDateTime dataEmissao;
    private double valorTotal;

    
    public Reserva(Sessao sessao, Cliente cliente, List<String> assentosReservados, double valorTotalCalculado) {
        
        this.idTicket = UUID.randomUUID().toString().substring(0, 8).toUpperCase(); 
        this.sessao = sessao;
        this.cliente = cliente;
        this.assentosReservados = assentosReservados;
        this.dataEmissao = LocalDateTime.now();
        this.valorTotal = valorTotalCalculado; 
    }

    // Getters

    public String getIdTicket() { 
        return idTicket; 
    }
    public Sessao getSessao() { 
        return sessao; 
    }
    public Cliente getCliente() { 
        return cliente; 
    }
    public List<String> getAssentosReservados() { 
        return assentosReservados; 
    }
    public LocalDateTime getDataEmissao() { 
        return dataEmissao; 
    }
    public double getValorTotal() { 
        return valorTotal; 
    }
}