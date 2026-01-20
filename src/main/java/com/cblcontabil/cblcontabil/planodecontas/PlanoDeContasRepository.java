package com.cblcontabil.cblcontabil.planodecontas;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlanoDeContasRepository extends JpaRepository<PlanoDeContasModel, Long> {
    Optional<PlanoDeContasModel> findByCodigo(int codigo);
}
