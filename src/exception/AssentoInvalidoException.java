package exception;

public class AssentoInvalidoException extends CinemaException {

    public AssentoInvalidoException() {
        super("O assento informado é inválido.");
    }

}