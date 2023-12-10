package com.iPizza.entregador.rule;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iPizza.entregador.validator.TipoPizzaValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoPizzaValidator.class)
public @interface TamanhoPizza {
    String message() default "Tamanho da pizza deve ser grande ou media";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
