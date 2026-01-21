package com.cblcontabil.cblcontabil.planodecontas;

import org.springframework.stereotype.Component;

@Component
public class PlanoDeContasMapper {
    public PlanoDeContasModel map(PlanoDeContasDTO dto) {

        PlanoDeContasModel model = new PlanoDeContasModel();

        model.setId(dto.getId());
        model.setCodigo(dto.getCodigo());
        model.setClassificacao(dto.getClassificacao());
        model.setDescricao(dto.getDescricao());
        model.setGrau(dto.getGrau());
        model.setEmpresa(dto.getEmpresa());

        return model;
    }

    public PlanoDeContasDTO map(PlanoDeContasModel model) {

        PlanoDeContasDTO dto = new PlanoDeContasDTO();

        dto.setId(model.getId());
        dto.setCodigo(model.getCodigo());
        dto.setClassificacao(model.getClassificacao());
        dto.setDescricao(model.getDescricao());
        dto.setGrau(model.getGrau());
        dto.setEmpresa(model.getEmpresa());

        return dto;
    }
}
