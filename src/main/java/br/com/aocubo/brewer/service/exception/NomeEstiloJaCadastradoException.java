package br.com.aocubo.brewer.service.exception;

/**
 * Created by alessandro on 09/04/17.
 */
public class NomeEstiloJaCadastradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NomeEstiloJaCadastradoException(String mensagem) {
        super(mensagem);
    }


}
