package com.gerencial.projetos.crud.aplicacao.infra.service;

import com.gerencial.projetos.crud.aplicacao.dto.request.TarefaRequest;
import com.gerencial.projetos.crud.aplicacao.dto.response.TarefaResponse;
import org.springframework.stereotype.Service;

@Service
public interface TarefaService {

    TarefaResponse novaTarefa(TarefaRequest tarefaRequest);


}
