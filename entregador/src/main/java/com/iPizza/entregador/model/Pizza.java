package com.iPizza.entregador.model;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class Pizza implements Serializable {

    @Column(nullable = false)
    private String tamanho;

    private Double preco;

    @ManyToOne
    @JoinColumn(name = "sabor1", nullable = false)
    private Sabor sabor1;

    @ManyToOne
    @JoinColumn(name = "sabor2", nullable = true)
    private Sabor sabor2;

    public void calcValor() {
        if (tamanho.equals("media")) {
            this.calcValorMedia();
        } else {
            this.calcValorGrande();
        }
    }

    private void calcValorMedia() {
        this.preco = this.sabor1.getPrecoM();
    }

    private void calcValorGrande() {
        Double total = this.sabor1.getPrecoG();

        if (this.sabor2 != null) {
            total = (total + this.sabor2.getPrecoG()) / 2;
        }
        this.preco = total;
    }

}
