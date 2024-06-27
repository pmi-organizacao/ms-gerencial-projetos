package com.gerencial.projetos.crud.aplicacao.controller;


import com.gerencial.projetos.crud.aplicacao.dto.request.*;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import com.gerencial.projetos.crud.aplicacao.infra.service.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projeto")
public class ProjetoController {

    @Autowired
    private ProjetoService projetoService;


    @PostMapping("/criar/novo-projeto")
    public ResponseEntity<ProjetoTarefasResponse> novoProjeto(@RequestBody ProjetoRequest projetoRequest) {
        return ResponseEntity.ok(projetoService.registrarNovoProjeto(projetoRequest));
    }

    @GetMapping("/buscar/projeto-por-codigo/{codigo}")
    public ResponseEntity<ProjetoTarefasResponse> buscarProjetoPorCodigo(@RequestParam String codigo) {
        return ResponseEntity.ok(projetoService.buscarProjetoPeloCodigo(codigo));
    }

    @GetMapping("/buscar/todos")
    public ResponseEntity<List<ProjetoTarefasResponse>> buscarProjetos() {
        return ResponseEntity.ok(projetoService.buscarTodosProjetos());
    }

    @PostMapping("/adicionar/os/{codigoProjeto}")
    public ResponseEntity<ProjetoTarefasResponse> adicionarTarefaAoProjeto(@RequestBody OrdemDeServicoRequest ordemDeServicoRequest, @PathVariable String codigoProjeto) {
        return ResponseEntity.ok(projetoService.adicionarOrdemDeServico(ordemDeServicoRequest, codigoProjeto));
    }

    @PostMapping("/adicionar/sprint/{codigoDaOs}")
    public ResponseEntity<ProjetoTarefasResponse> adicionarTarefaAoProjeto(@RequestBody SprintRequest sprintRequest, @PathVariable Long codigoDaOs) {
        return ResponseEntity.ok(projetoService.adicionarSprint(sprintRequest, codigoDaOs));
    }

    @PostMapping("/adicionar/caso-de-uso/{codigoDaSprint}")
    public ResponseEntity<ProjetoTarefasResponse> adicionarTarefaAoProjeto(@RequestBody CasoDeUsoRequest casoDeUsoRequest, @PathVariable Long codigoDaSprint) {
        return ResponseEntity.ok(projetoService.adicionarCasoDeUso(casoDeUsoRequest, codigoDaSprint));
    }
    @PatchMapping("/atualizar/porcentagem-tarefa/{idTarefa}")
    public ResponseEntity<ProjetoTarefasResponse> alterarPorcentagem(@PathVariable Long idTarefa, @RequestBody AlterarPorcentagemRequest alterarPorcentagemRequest) {
        return ResponseEntity.ok(projetoService.alterarPorcentagem(idTarefa, alterarPorcentagemRequest));
    }

    @PostMapping("/adicionar/tarefa-no-caso-de-usp/{idCasoDeUso}")
    public ResponseEntity<ProjetoTarefasResponse> adicionarTarefa(@PathVariable Long idCasoDeUso, @RequestBody TarefaRequest tarefaRequest) {
        return ResponseEntity.ok(projetoService.adicionarTarefa(idCasoDeUso, tarefaRequest));
    }


}
