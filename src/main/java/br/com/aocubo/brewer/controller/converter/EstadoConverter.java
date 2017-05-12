package br.com.aocubo.brewer.controller.converter;

import br.com.aocubo.brewer.model.Estado;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 29/04/17.
 */

public class EstadoConverter implements Converter<String, Estado> {

    @Override
    public Estado convert(String id) {
        if(!StringUtils.isEmpty(id)){
            Estado estado = new Estado();
            estado.setId(Long.valueOf(id));
            return estado;
        }
        return null;
    }

}
