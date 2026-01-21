package com.cblcontabil.cblcontabil.planodecontas;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlanoDeContasDTO {

    private Long id;
    private int codigo;
    private String classificacao;
    private String descricao;
    private int grau;
    private String empresa;

}
