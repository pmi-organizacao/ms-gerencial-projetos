package com.gerencial.projetos.crud.aplicacao.infra.service;

import com.gerencial.projetos.crud.aplicacao.dto.request.NotificacaoRequest;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoTarefaResponse;
import org.springframework.stereotype.Service;

@Service
public interface NotificacaoService {

    NotificacaoResponse addAnotacoes(NotificacaoRequest notificacaoRequest);

    NotificacaoTarefaResponse buscarNotificacaoTarefa(Long idTarefa);

}
