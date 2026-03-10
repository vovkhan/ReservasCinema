package exception;

public class SessaoNaoEncontradaException extends CinemaException {

    public SessaoNaoEncontradaException() {
        super("Sessão não encontrada.");
    }
}