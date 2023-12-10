package com.iPizza.entregador.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

import com.iPizza.entregador.model.Entregador;

public interface EntregadorRepository extends JpaRepository<Entregador, UUID> {
}
