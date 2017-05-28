package br.com.aocubo.brewer.service.event.cerveja;

import br.com.aocubo.brewer.model.Cerveja;
import org.springframework.util.StringUtils;

/**
 * Created by alessandro on 27/05/17.
 */

public class CervejaSalvaEvent {

    private Cerveja cerveja;

    public CervejaSalvaEvent(Cerveja cerveja) {
        this.cerveja = cerveja;
    }

    public Cerveja getCerveja() {
        return cerveja;
    }

    public boolean temFoto(){
        return !StringUtils.isEmpty(cerveja.getFoto());
    }

}
