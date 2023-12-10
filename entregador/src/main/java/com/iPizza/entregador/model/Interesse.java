package com.iPizza.entregador.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.UUID;

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
public class Interesse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private Date criadoEm;

    @Column(nullable = false)
    private Boolean notificado;

    @Column(nullable = true)
    private Date notificadoEm;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_interesse_cliente", foreignKeyDefinition = "FOREIGN KEY (clienteId) REFERENCES cliente(id) ON DELETE CASCADE"))
    private Cliente clienteId;

    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "fk_interesse_sabor", foreignKeyDefinition = "FOREIGN KEY (saborId) REFERENCES sabores(id) ON DELETE CASCADE"))
    private Sabor saborId;

}

