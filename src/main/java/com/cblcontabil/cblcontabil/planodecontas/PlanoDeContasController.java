package com.cblcontabil.cblcontabil.planodecontas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/boasvindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa")
    public String boasVindas() {
        return "Boas vindas";
    }

    @PostMapping
    @Operation(summary = "Cria uma nova conta", description = "Essa rota cria uma nova conta contábil e inseri no banco de dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Conta contábil criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Erro na criação da conta contábil")
    })
    public ResponseEntity<String> criarContas(
            @Parameter(description = "Usuario manda os dados da nova conta no corpo da requisicao ")
            @RequestBody PlanoDeContasDTO conta) {
        PlanoDeContasDTO novaConta = planoDeContasService.criarConta(conta);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Conta criada com sucesso: " + novaConta.getDescricao() + " (Código): " + novaConta.getCodigo());
    }

    @GetMapping
    public ResponseEntity<List<PlanoDeContasDTO>>  listarConta() {
        List<PlanoDeContasDTO> contas = planoDeContasService.listarConta();
        return ResponseEntity.ok(contas);
    }

    @GetMapping("/{codigo}")
    @Operation(summary = "Busca uma conta pelo codigo", description = "Essa rota busca uma conta contabil no banco de dados pelo codigo enviado pelo user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta contabil foi encontrada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro na busca da conta contabil")
    })
    public ResponseEntity<?> buscarPorCodigo(@PathVariable int codigo) {
        PlanoDeContasDTO contaCodigo = planoDeContasService.buscarContaPorCodigo(codigo)
                .orElse(null);

        if (contaCodigo != null){
            return ResponseEntity.ok(contaCodigo);

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conta com o id: " + codigo + " não encontrada nos nossos registros.");
        }
    }

    @PutMapping("/{codigo}")
    @Operation(summary = "Altera conta pelo codigo", description = "Essa rota altera uma conta contabil no banco de dados pelo codigo enviado pelo user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Conta contabil foi alterada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Erro na alteração da conta contabil")
    })
    public ResponseEntity<?> alterarConta(
            @Parameter(description = "Usuario manda o codigo no caminho da requisiçao")
            @PathVariable int codigo,
            @Parameter(description =  "usuario manda os dados da conta a ser atualizada no corpo da requisicao")
            @RequestBody PlanoDeContasDTO planoDeContasDTO) {

        PlanoDeContasDTO conta = planoDeContasService.atualizarConta(codigo, planoDeContasDTO);

        if (conta != null){
            return ResponseEntity.ok(conta);

        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conta com o id: " + codigo + " não encontrada nos nossos registros.");
        }

    }

    @DeleteMapping("/{codigo}")
        public ResponseEntity<String> deletarConta(@PathVariable int codigo) {
        PlanoDeContasDTO conta = planoDeContasService.buscarContaPorCodigo(codigo)
            .orElse(null);
        if (conta != null){
            planoDeContasService.deletarPorCodigo(codigo);
            return ResponseEntity.ok("Conta com o codigo " + codigo + " excluída com sucesso.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Conta com o codigo " + codigo + "não encontrada.");
        }

    }
}
