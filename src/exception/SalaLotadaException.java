package exception;

public class SalaLotadaException extends CinemaException {

    public SalaLotadaException() {
        super("Não há mais assentos disponíveis nessa sessão.");
    }

}