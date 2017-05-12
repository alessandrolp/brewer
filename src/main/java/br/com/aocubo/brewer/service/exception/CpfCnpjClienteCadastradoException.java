package br.com.aocubo.brewer.service.exception;

/**
 * Created by alessandro on 29/04/17.
 */
public class CpfCnpjClienteCadastradoException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CpfCnpjClienteCadastradoException(String message) {
        super(message);
    }

}
