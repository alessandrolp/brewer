package br.com.aocubo.brewer.service;

import br.com.aocubo.brewer.model.Cerveja;
import br.com.aocubo.brewer.repository.Cervejas;
import br.com.aocubo.brewer.service.event.cerveja.CervejaSalvaEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by alessandro on 08/04/17.
 */

@Service
public class CervejaService {

    @Autowired
    private Cervejas cervejas;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Transactional
    public void salvar(Cerveja cerveja) {
        cervejas.save(cerveja);
        publisher.publishEvent(new CervejaSalvaEvent(cerveja));
    }

}
