package br.com.aocubo.brewer.thymeleaf;

import br.com.aocubo.brewer.thymeleaf.processor.*;
import org.thymeleaf.dialect.AbstractProcessorDialect;
import org.thymeleaf.processor.IProcessor;
import org.thymeleaf.standard.StandardDialect;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by alessandro on 16/04/17.
 */
public class BrewerDialect extends AbstractProcessorDialect {

    public BrewerDialect() {
        super("Aocubo Brewer", "brewer", StandardDialect.PROCESSOR_PRECEDENCE);
    }

    @Override
    public Set<IProcessor> getProcessors(String dialectPrefix) {
        final Set<IProcessor> processadores = new HashSet<>();
        processadores.add(new ClassForErrorAttributeTagProcessor(dialectPrefix));
        processadores.add(new MessageElementTagProcessor(dialectPrefix));
        processadores.add(new OrderElementTagProcessor(dialectPrefix));
        processadores.add(new PaginationElementTagProcessor(dialectPrefix));
        processadores.add(new MenuAttributeTagProcessor(dialectPrefix));
        return processadores;
    }

}
