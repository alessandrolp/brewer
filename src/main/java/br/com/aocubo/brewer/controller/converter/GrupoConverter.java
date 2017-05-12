package br.com.aocubo.brewer.controller.converter;

import br.com.aocubo.brewer.model.Grupo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 08/05/17.
 */

public class GrupoConverter implements Converter<String, Grupo> {

    @Override
    public Grupo convert(String id) {
        if(!StringUtils.isEmpty(id)){
            Grupo grupo = new Grupo();
            grupo.setId(Long.valueOf(id));
            return grupo;
        }
        return null;
    }

}
