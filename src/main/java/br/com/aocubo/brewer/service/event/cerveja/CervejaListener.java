package br.com.aocubo.brewer.service.event.cerveja;

import br.com.aocubo.brewer.storage.FotoStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Created by alessandro on 28/05/17.
 */

@Component
public class CervejaListener {

    @Autowired
    private FotoStorage fotoStorage;

    @EventListener(condition = "#event.temFoto()")
    public void cervejaSalva(CervejaSalvaEvent event){
        fotoStorage.salvar(event.getCerveja().getFoto());
    }

}
