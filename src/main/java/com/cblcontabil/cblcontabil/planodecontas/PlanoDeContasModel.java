package com.cblcontabil.cblcontabil.planodecontas;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_planodecontas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDeContasModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private int codigo;

    @Column(unique = true)
    private String classificacao;

    private String descricao;

    private int grau;
}
