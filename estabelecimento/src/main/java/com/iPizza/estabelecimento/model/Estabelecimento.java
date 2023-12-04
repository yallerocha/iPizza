package com.iPizza.estabelecimento.model;

import java.util.List;

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
public class Estabelecimento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "estabelecimento")
    @Embedded
    @JsonIgnore
    private List<Associacao> associacoes;
    
    @Column(name = "codigo", nullable = false)
    private String codigo;

    @Column(name = "email")
    private String email;
}
