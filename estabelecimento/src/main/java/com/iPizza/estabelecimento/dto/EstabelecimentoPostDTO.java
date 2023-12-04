package com.iPizza.estabelecimento.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoPostDTO {
    @NotNull
    @NotBlank
    @JsonProperty("nome")
    private String nome;
    @NotNull
    @NotBlank
    @JsonProperty("cnpj")
    private String cnpj;
    @NotNull
    @NotBlank
    @JsonProperty("codigo")
    private String codigo;
    @NotNull
    @NotBlank
    @JsonProperty("email")
    private String email;
}
