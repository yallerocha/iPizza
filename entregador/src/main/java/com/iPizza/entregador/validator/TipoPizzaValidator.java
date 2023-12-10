package com.iPizza.entregador.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import com.iPizza.entregador.rule.TamanhoPizza;

public class TipoPizzaValidator implements ConstraintValidator<TamanhoPizza, String> {

    @Override
    public void initialize(TamanhoPizza annotation) {
    }
    public boolean isValid(String tamanhoPizza, ConstraintValidatorContext context) {
        String tamanhosPizza[] = {null,"media", "grande"} ;
        List<String> list = Arrays.asList(tamanhosPizza);
        return list.contains(tamanhoPizza) ;
    }
}
