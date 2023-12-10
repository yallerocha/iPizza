package com.iPizza.entregador.rule;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iPizza.entregador.validator.MeioDePagamentoValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MeioDePagamentoValidator.class)
public @interface MeioDePagamento {
    String message() default "Meio de Pagamento deve ser cartão de débito, cartão de crédito ou Pix";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
