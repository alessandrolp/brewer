package br.com.aocubo.brewer.service.exception;

/**
 * Created by alessandro on 08/05/17.
 */
public class SenhaObrigatoriaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public SenhaObrigatoriaException(String message) {
        super(message);
    }
}
