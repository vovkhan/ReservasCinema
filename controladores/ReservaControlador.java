package controladores;
import modelos.Cliente;
import modelos.Reserva;
import modelos.Sessao;
import java.util.List;

public class ReservaControlador {
    public Reserva processarVenda(Cliente cliente, Sessao sessao, List<String> assentos) {
        if(assentos.size() > 5 ){
            throw new IllegalArgumentException("Erro: O limite maximo é de 6 Lugares. Por favor, tente novamente");
    
        }
        double precoBase = 25.0;
        double total = precoBase * assentos.size();

        if(cliente != null){
            if(cliente.getIdade() < 18 || cliente.getIDade() >= 60){
                total = total * 0.5;
            }else if (cliente.getIngressosAdquiridos() >= 5){
                total = total * 0.8;
            }

        }

        Reserva novaReserva = new Reserva(sessao, cliente, assentos, total);
        return novaReserva;
    }
}
