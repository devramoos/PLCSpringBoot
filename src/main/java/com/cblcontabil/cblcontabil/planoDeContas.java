package com.cblcontabil.cblcontabil;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "tb_planodecontas")
public class planoDeContas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
