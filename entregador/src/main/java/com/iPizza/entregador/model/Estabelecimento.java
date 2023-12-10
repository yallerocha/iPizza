package com.iPizza.entregador.model;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class Estabelecimento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    
    @Column(nullable = false)
    private String codigo;

    private String email;

    @Embedded
    @JsonIgnore
    @OneToMany(mappedBy = "estabelecimentoId")
    private List<Associacao> associacoes;
}
