package com.iPizza.estabelecimento.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoPostGetPutDTO implements Serializable {

    @NotBlank
    @Size(min = 6, max = 6, message = "O código de acesso deve ter exatamente 6 dígitos")
    private String codigo;

    @NotBlank
    private String email;
}
    
    