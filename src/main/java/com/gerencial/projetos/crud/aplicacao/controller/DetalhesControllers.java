package com.gerencial.projetos.crud.aplicacao.controller;


import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheCasoDeUsoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheOsResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheProjetoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheSprintResponse;
import com.gerencial.projetos.crud.aplicacao.infra.service.DetalhamentoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/detalhes")
public class DetalhesControllers {

    @Autowired
    private DetalhamentoServices detalhamentoServices;


    @GetMapping("/projeto")
    public ResponseEntity<List<DetalheProjetoResponse>> detalhesDeProjeto(){
        return ResponseEntity.ok(detalhamentoServices.getDetalhamentoProjeto());
    }
    @GetMapping("/ordem-de-servico")
    public ResponseEntity<List<DetalheOsResponse>> detalhesDeProjeto(@RequestParam(value = "nomeDoProjeto") String nome){
        return ResponseEntity.ok(detalhamentoServices.getDetalhamentoOs(nome));
    }

    @GetMapping("/sprint")
    public ResponseEntity<List<DetalheSprintResponse>> detalheSprints(
            @RequestParam(value = "id") Long id){
        return ResponseEntity.ok(detalhamentoServices.getDetalhamentoSprint(id));
    }

    @GetMapping("/casosDeUso")
    public ResponseEntity<List<DetalheCasoDeUsoResponse>> detalheCasosDeUso(
            @RequestParam(value = "id") Long id){
        return ResponseEntity.ok(detalhamentoServices.getDetalhamentoCasoDeUso(id));
    }
}
