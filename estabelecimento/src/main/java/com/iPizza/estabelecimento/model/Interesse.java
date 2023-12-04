package com.iPizza.estabelecimento.model;


import java.sql.Date;

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
@Table(name = "Interesses")
public class Interesse {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonProperty("id")
    private Long id;

    @ManyToOne
    @JsonProperty("cliente")
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "fk_interesse_cliente", foreignKeyDefinition = "FOREIGN KEY (cliente_id) REFERENCES cliente(id) ON DELETE CASCADE"))
    private Cliente cliente;

    @ManyToOne
    @JsonProperty("sabor")
    @JoinColumn(name = "sabor_id", foreignKey = @ForeignKey(name = "fk_interesse_sabor", foreignKeyDefinition = "FOREIGN KEY (sabor_id) REFERENCES sabores(id) ON DELETE CASCADE"))
    private Sabor sabor;

    @JsonProperty("criadoEm")
    @Column(nullable = false)
    private Date criadoEm;

    @JsonProperty("notificado")
    @Column(nullable = false)
    private Boolean notificado;

    @JsonProperty("notificadoEm")
    @Column(nullable = true)
    private Date notificadoEm;

}

