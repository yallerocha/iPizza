package com.iPizza.estabelecimento.repository;

import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import com.iPizza.estabelecimento.model.Estabelecimento;

public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, UUID> {
}
