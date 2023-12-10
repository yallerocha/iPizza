package com.iPizza.entregador.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import com.iPizza.entregador.rule.TipoSaborPizza;

public class TipoSaborPizzaValidator implements ConstraintValidator<TipoSaborPizza, String> {

    @Override
    public void initialize(TipoSaborPizza annotation) {
    }

    @Override
    public boolean isValid(String tipo, ConstraintValidatorContext context) {
        String tiposSabor[] = {null,"salgado", "doce"} ;
        List<String> list = Arrays.asList(tiposSabor);
        return list.contains(tipo) ;
    }

}
