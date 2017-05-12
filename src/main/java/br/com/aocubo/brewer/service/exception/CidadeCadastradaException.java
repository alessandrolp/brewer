package br.com.aocubo.brewer.service.exception;

/**
 * Created by alessandro on 06/05/17.
 */
public class CidadeCadastradaException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public CidadeCadastradaException(String message){
        super(message);
    }
}
