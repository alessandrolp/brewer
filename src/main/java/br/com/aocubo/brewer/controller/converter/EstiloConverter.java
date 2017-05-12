package br.com.aocubo.brewer.controller.converter;

import br.com.aocubo.brewer.model.Estilo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 08/04/17.
 */

public class EstiloConverter implements Converter<String, Estilo> {

    @Override
    public Estilo convert(String id) {
        if(!StringUtils.isEmpty(id)){
            Estilo estilo = new Estilo();
            estilo.setId(Long.valueOf(id));
            return estilo;
        }
        return null;
    }

}
