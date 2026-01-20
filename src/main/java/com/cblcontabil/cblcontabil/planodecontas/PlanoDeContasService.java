package com.cblcontabil.cblcontabil.planodecontas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanoDeContasService {

    private PlanoDeContasRepository planoDeContasRepository;

    public PlanoDeContasService(PlanoDeContasRepository planoDeContasRepository) {
        this.planoDeContasRepository = planoDeContasRepository;
    }

    public List<PlanoDeContasModel> listarConta(){
        return planoDeContasRepository.findAll();
    }

    public Optional<PlanoDeContasModel> buscarContaPorCodigo(int codigo){
        return planoDeContasRepository.findByCodigo(codigo);
    }

}
