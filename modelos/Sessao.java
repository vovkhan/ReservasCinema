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
    public void setHorario(LocalDateTime horario){
        this.horario = horario;
    }

    public boolean isLugarVago(int linha, int coluna){
        return sala.isLugarVago(linha, coluna);
    }

    public void reservarLugar(int linha, int coluna){
        if(isLugarVago(linha, coluna)){
            sala.reservarLugar(linha, coluna);
        
        }else{
            throw new IllegalArgumentException("Erro: Lugar já foi reservado. Por favor, escolha outro lugar.");
        }
    }
    
}


