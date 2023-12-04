package com.iPizza.estabelecimento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoPostPutDTO {
    @NotNull
    @NotBlank
    @JsonProperty("codigo")
    @Size(min = 6, max = 6, message = "O código de acesso deve ter exatamente 6 dígitos")
    private String codigo;
    @NotNull
    @NotBlank
    @JsonProperty("email")
    private String email;
}