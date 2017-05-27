package br.com.aocubo.brewer.controller.handler;

import br.com.aocubo.brewer.service.exception.NomeEstiloJaCadastradoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by alessandro on 27/05/17.
 */

@ControllerAdvice
public class ControllerAdviceExceptionHandler {

    @ExceptionHandler(NomeEstiloJaCadastradoException.class)
    public ResponseEntity<String> handleNomeEstiloJaCadastradoException(NomeEstiloJaCadastradoException e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e){
        return ResponseEntity.badRequest().build();
    }

}
