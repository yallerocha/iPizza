package com.iPizza.estabelecimento.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty
    private Long id;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Interesse> interesses;
    
    @Column(name = "nome", nullable = false)
    @JsonProperty
    private String nome;
    @Column(name = "email", nullable = false)
    @JsonProperty
    private String email;
    @Embedded
    @JsonProperty
    private Endereco endereco;
    @Column(name = "cod", nullable = false)
    @JsonIgnore
    private String cod;
}