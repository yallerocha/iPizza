package com.iPizza.entregador.rule;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iPizza.entregador.validator.TipoSaborPizzaValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoSaborPizzaValidator.class)
public @interface TipoSaborPizza {
    String message() default "Tipo do sabor da pizza deve ser salgado ou doce";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}