package com.cblcontabil.cblcontabil.planodecontas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/planodecontas")
public class PlanoDeContasController {

    private PlanoDeContasService planoDeContasService;

    public PlanoDeContasController(PlanoDeContasService planoDeContasService) {
        this.planoDeContasService = planoDeContasService;
    }

    @GetMapping
    public String boasVindas(){
        return "Boas vindas";
    }

    @PostMapping("/criar")
    public PlanoDeContasDTO criarContas(@RequestBody PlanoDeContasDTO planoDeContasDTO){
        return planoDeContasService.criarConta(planoDeContasDTO);
    }

    @GetMapping("/listar")
    public List<PlanoDeContasDTO> listarConta(){
        return planoDeContasService.listarConta();
    }

    @GetMapping("/buscarcontaporcodigo/{codigo}")
    public PlanoDeContasDTO buscarPorCodigo(@PathVariable int codigo) {
        return planoDeContasService.buscarContaPorCodigo(codigo)
                .orElse(null);
    }

    @PutMapping("/alterarconta/{codigo}")
    public PlanoDeContasDTO alterarConta(@PathVariable int codigo, @RequestBody PlanoDeContasDTO planoDeContasDTO){
        return planoDeContasService.atualizarConta(codigo, planoDeContasDTO);
    }

    @DeleteMapping("/deletarconta/{codigo}")
    public void deletarConta(@PathVariable int codigo){
        PlanoDeContasDTO conta = planoDeContasService.buscarContaPorCodigo(codigo)
                .orElse(null);

        if (conta != null) {
            planoDeContasService.deletarPorCodigo(codigo);
        } else {
            throw new RuntimeException("Conta não encontrada");
        }
    }

}
