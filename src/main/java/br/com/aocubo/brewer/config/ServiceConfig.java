package br.com.aocubo.brewer.config;

import br.com.aocubo.brewer.service.CervejaService;
import br.com.aocubo.brewer.storage.FotoStorage;
import br.com.aocubo.brewer.storage.local.FotoStorageLocal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by alessandro on 08/04/17.
 */

@Configuration
@ComponentScan(basePackageClasses = CervejaService.class)
public class ServiceConfig {

    @Bean
    public FotoStorage fotoStorage(){
        return new FotoStorageLocal();
    }

}
