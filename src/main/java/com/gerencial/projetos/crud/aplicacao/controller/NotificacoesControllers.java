package com.gerencial.projetos.crud.aplicacao.controller;

import com.gerencial.projetos.crud.aplicacao.dto.request.NotificacaoRequest;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoTarefaResponse;
import com.gerencial.projetos.crud.aplicacao.infra.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/notificacao")
public class NotificacoesControllers {

    @Autowired
    private NotificacaoService notificacaoService;

    @PostMapping
    public ResponseEntity<NotificacaoResponse> adicionarNovaNotificacao(@RequestBody NotificacaoRequest request) {
        return ResponseEntity.ok(notificacaoService.addAnotacoes(request));
    }

    @GetMapping("{id}")
    public ResponseEntity<NotificacaoTarefaResponse> buscarNotificacaoDaTarefa(@PathVariable Long id) {
        return ResponseEntity.ok(notificacaoService.buscarNotificacaoTarefa(id));
    }

}
