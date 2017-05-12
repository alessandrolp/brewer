package br.com.aocubo.brewer.controller.converter;

import br.com.aocubo.brewer.model.Cidade;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 29/04/17.
 */

public class CidadeConverter implements Converter<String, Cidade> {

    @Override
    public Cidade convert(String id) {
        if(!StringUtils.isEmpty(id)){
            Cidade cidade = new Cidade();
            cidade.setId(Long.valueOf(id));
            return cidade;
        }
        return null;
    }

}
