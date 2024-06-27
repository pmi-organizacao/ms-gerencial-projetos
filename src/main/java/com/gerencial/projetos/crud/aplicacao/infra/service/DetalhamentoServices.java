package com.gerencial.projetos.crud.aplicacao.infra.service;

import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheCasoDeUsoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheOsResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheProjetoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.DetalheSprintResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetalhamentoServices {

    List<DetalheProjetoResponse> getDetalhamentoProjeto();

    List<DetalheOsResponse> getDetalhamentoOs(String idProjeto);


    List<DetalheSprintResponse> getDetalhamentoSprint(Long id);

    List<DetalheCasoDeUsoResponse> getDetalhamentoCasoDeUso(Long id);

}
