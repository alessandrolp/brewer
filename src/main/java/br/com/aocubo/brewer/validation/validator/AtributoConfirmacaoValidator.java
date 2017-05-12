package br.com.aocubo.brewer.validation.validator;

import br.com.aocubo.brewer.validation.AtributoConfirmacao;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

/**
 * Created by alessandro on 07/05/17.
 */

public class AtributoConfirmacaoValidator implements ConstraintValidator<AtributoConfirmacao, Object> {

    private String atributo;

    private String atributoConfirmacao;

    @Override
    public void initialize(AtributoConfirmacao constraintAnnotation) {
        this.atributo = constraintAnnotation.atributo();
        this.atributoConfirmacao = constraintAnnotation.atributoConfirmacao();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        boolean valido = false;
        try {
            Object valorAtributo = BeanUtils.getProperty(object, this.atributo);
            Object valorAtributoConfirmacao = BeanUtils.getProperty(object, this.atributoConfirmacao);
            valido = ambosSaoNulos(valorAtributo, valorAtributoConfirmacao) || ambosSaoIguais(valorAtributo, valorAtributoConfirmacao);
        } catch (Exception e){
            throw new RuntimeException("Erro recuperando valores dos atributos", e);
        }
        if(!valido){
            context.disableDefaultConstraintViolation();
            String mensagem = context.getDefaultConstraintMessageTemplate();
            ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(mensagem);
            violationBuilder.addPropertyNode(atributoConfirmacao).addConstraintViolation();
        }
        return valido;
    }

    private boolean ambosSaoNulos(Object valorAtributo, Object valorAtributoConfirmacao) {
        return valorAtributo == null && valorAtributoConfirmacao == null;
    }

    private boolean ambosSaoIguais(Object valorAtributo, Object valorAtributoConfirmacao) {
        return valorAtributo != null && valorAtributo.equals(valorAtributoConfirmacao);
    }

}
