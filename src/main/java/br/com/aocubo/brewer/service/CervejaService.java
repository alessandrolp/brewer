package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.Cervejas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alessandro on 08/04/17.
 */

@Service
public class CervejaService {

    @Autowired
    private Cervejas cervejas;

    @Transactional
    public void salvar(Cerveja cerveja) {
        cervejas.save(cerveja);
    }

}
