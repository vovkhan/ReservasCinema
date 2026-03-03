package modelos;
import java.time.LocalDateTime;

public class Sessao {

    private Filme filme;
    private Sala sala;
    private LocalDateTime horario;
    private String estado;

    public Sessao(Filme filme, Sala sala, LocalDateTime horario) {
        this.filme = filme;
        this.sala = sala;
        this.horario = horario;
        this.estado = "Disponível";
    }


    //getters
    public Filme getFilme(){
        return filme;
    }

    public Sala getSala(){
        return sala;
    }

    public LocalDateTime getHorario(){
        return horario;
    }
    
    public String getEstado(){
        return estado;
    }

    //setters
    public void setEstado(String estado){
        this.estado = estado;
    }
    public void Horario(LocalDateTime horario){
        this.horario = horario;
    }

    public void islugarVago(int linha, int coluna){
        if(sala.isLugarVago(linha, coluna)){
            sala.reservarLugar(linha, coluna);
            this.estado = "Ocupada";
        } else {
            System.out.println("Lugar já ocupado.");
        }
    }

    
    
}
