package exception;

public class SessaoNaoEncontradaException extends Exception {

    public SessaoNaoEncontradaException() {
        super("Sessão não encontrada.");
    }
}