package com.iPizza.entregador.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import com.iPizza.entregador.rule.TipoVeiculo;

public class TipoVeiculoValidator implements ConstraintValidator<TipoVeiculo, String> {

    @Override
    public void initialize(TipoVeiculo annotation) {
    }

    @Override
    public boolean isValid(String tipoVeiculo, ConstraintValidatorContext context) {
        String tiposVeiculo[] = {null,"moto", "carro"} ;
        List<String> list = Arrays.asList(tiposVeiculo);
        return list.contains(tipoVeiculo) ;
    }

}
