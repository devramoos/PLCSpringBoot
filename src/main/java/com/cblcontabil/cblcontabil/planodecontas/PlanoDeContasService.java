package com.cblcontabil.cblcontabil.planodecontas;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PlanoDeContasService {

    private PlanoDeContasRepository planoDeContasRepository;
    private PlanoDeContasMapper planoDeContasMapper;

    public PlanoDeContasService(PlanoDeContasRepository planoDeContasRepository, PlanoDeContasMapper planoDeContasMapper) {
        this.planoDeContasRepository = planoDeContasRepository;
        this.planoDeContasMapper = planoDeContasMapper;
    }

    public List<PlanoDeContasDTO> listarConta(){
        List<PlanoDeContasModel> contas = planoDeContasRepository.findAll();
        return contas.stream().map(planoDeContasMapper::map)
                .collect(Collectors.toList());
    }

    public Optional<PlanoDeContasDTO> buscarContaPorCodigo(int codigo){
        return planoDeContasRepository.findByCodigo(codigo).map(planoDeContasMapper::map);
    }

    public PlanoDeContasDTO criarConta(PlanoDeContasDTO planoDeContasDTO){
        PlanoDeContasModel conta = planoDeContasMapper.map(planoDeContasDTO);
        conta = planoDeContasRepository.save(conta);
        return planoDeContasMapper.map(conta);
    }

    public void deletarPorCodigo(int codigo) {
        PlanoDeContasModel contaBanco = planoDeContasRepository.findByCodigo(codigo)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada"));

        planoDeContasRepository.delete(contaBanco);
    }

    public PlanoDeContasDTO atualizarConta(int codigo, PlanoDeContasDTO planoDeContasDTO){
        Optional<PlanoDeContasModel> conta = planoDeContasRepository.findByCodigo(codigo);
        if (conta.isPresent()){
            PlanoDeContasModel contaAtualizada = planoDeContasMapper.map(planoDeContasDTO);
            PlanoDeContasModel contaSalva = planoDeContasRepository.save(contaAtualizada);
            return planoDeContasMapper.map(contaSalva);
        }else {
            return null;
        }
    }

}
