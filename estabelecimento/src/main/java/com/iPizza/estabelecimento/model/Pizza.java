package com.iPizza.estabelecimento.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class Pizza {

    @ManyToOne
    @JoinColumn(name = "sabor1", nullable = false)
    @JsonProperty("sabor1")
    private Sabor sabor1;

    @ManyToOne
    @JoinColumn(name = "sabor2", nullable = true)
    @JsonProperty("sabor2")
    private Sabor sabor2;

    @Column(name = "tamanho", nullable = false)
    private String tamanho;

    @Column(name = "preco")
    private Double preco;

    public void calcValor(){
        if(tamanho.equals("media")){
            this.calcValorMedia();
        }else{
            this.calcValorGrande();
        }
    }

    private void calcValorMedia(){
        this.preco = this.sabor1.getPrecoM();
    }

    private void calcValorGrande(){
        Double total = this.sabor1.getPrecoG();

        if(this.sabor2 != null){
            total = (total + this.sabor2.getPrecoG()) / 2;
        }

        this.preco = total;
    }

}
