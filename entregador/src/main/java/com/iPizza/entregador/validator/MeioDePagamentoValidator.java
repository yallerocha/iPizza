package com.iPizza.entregador.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;

import com.iPizza.entregador.rule.MeioDePagamento;

public class MeioDePagamentoValidator implements ConstraintValidator<MeioDePagamento, String> {

    @Override
    public void initialize(MeioDePagamento annotation) {
    }

    @Override
    public boolean isValid(String meioDePagamento, ConstraintValidatorContext context) {
        String meiosDePagamentos[] = {null,"cartão de débito", "cartão de crédito", "Pix"} ;
        List<String> list = Arrays.asList(meiosDePagamentos);
        return list.contains(meioDePagamento) ;
    }
}
