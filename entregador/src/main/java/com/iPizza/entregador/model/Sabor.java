package com.iPizza.entregador.model;


import java.io.Serializable;
import java.util.List;
import java.util.UUID;

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
public class Sabor implements Serializable {

    @Id
    @JsonProperty("id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @OneToMany(mappedBy = "saborId")
    @JsonIgnore
    private List<Interesse> interesses;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Estabelecimento  estabelecimento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Double precoM;

    @Column(nullable = false)
    private Double precoG;

    @Column(nullable = false)
    private Boolean disponivel;

    public boolean isDisponivel(){
        return this.disponivel;
    }
}
