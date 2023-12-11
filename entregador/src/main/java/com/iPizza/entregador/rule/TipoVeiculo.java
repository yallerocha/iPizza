package com.iPizza.entregador.rule;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.iPizza.entregador.validator.TipoVeiculoValidator;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = TipoVeiculoValidator.class)
public @interface TipoVeiculo {

    String message() default "Tipo do veiculo deve ser moto ou carro";

    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
