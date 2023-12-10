package com.iPizza.entregador.validator;

import com.iPizza.entregador.rule.StatusEntregador;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

public class StatusEntregadorValidator implements ConstraintValidator<StatusEntregador, String> {

    @Override
    public void initialize(StatusEntregador annotation) {
    }
    public boolean isValid(String statusEntregador, ConstraintValidatorContext context) {
        String status[] = {"ativo", "descanso"} ;
        List<String> list = Arrays.asList(status);
        return list.contains(statusEntregador) ;
    }
}

