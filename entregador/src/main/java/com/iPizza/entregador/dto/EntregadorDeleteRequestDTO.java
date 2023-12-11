package com.iPizza.entregador.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EntregadorDeleteRequestDTO {

    @NotNull(message = "Codigo de acesso e obrigatorio")
    @Size(min = 6, max = 6, message = "O código de acesso deve ter exatamente 6 dígitos")
    private String codigo;
}
