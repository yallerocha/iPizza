package com.iPizza.entregador.rule;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iPizza.entregador.validator.StatusEntregadorValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = StatusEntregadorValidator.class)
public @interface StatusEntregador {
    String message() default "StatusEntregador deve ser 'ativo' ou 'descanso'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}