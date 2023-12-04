package com.iPizza.estabelecimento.model;


import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "sabores")
public class Sabor {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @OneToMany(mappedBy = "sabor")
    @JsonIgnore
    private List<Interesse> interesses;

    @ManyToOne
    @JoinColumn(name = "estabelecimento", nullable = false)
    @JsonProperty("estabelecimento")
    private Estabelecimento  estabelecimento;


    @JsonProperty("nome")
    @Column(nullable = false)
    private String nome;

    @JsonProperty("tipo")
    @Column(nullable = false)
    private String tipo;

    @JsonProperty("precoM")
    @Column(nullable = false)
    private Double precoM;

    @JsonProperty("precoG")
    @Column(nullable = false)
    private Double precoG;

    @JsonProperty("disponivel")
    @Column(nullable = false)
    private Boolean disponivel;

    public boolean isDisponivel(){
        return this.disponivel;
    }
}
