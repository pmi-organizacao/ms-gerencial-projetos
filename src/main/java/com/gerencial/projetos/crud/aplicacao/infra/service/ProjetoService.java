package com.gerencial.projetos.crud.aplicacao.infra.service;

import com.gerencial.projetos.crud.aplicacao.dto.request.*;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjetoService {

    ProjetoTarefasResponse registrarNovoProjeto(ProjetoRequest projetoRequest);
    ProjetoTarefasResponse adicionarOrdemDeServico(OrdemDeServicoRequest ordemDeServicoRequest, String codigoProjeto);
    ProjetoTarefasResponse adicionarSprint(SprintRequest tarefaRequest, Long codigoDaOs);
    ProjetoTarefasResponse adicionarCasoDeUso(CasoDeUsoRequest casoDeUsoRequest, Long idDaSprint);
    ProjetoTarefasResponse buscarProjetoPeloCodigo(String codigo);
    List<ProjetoTarefasResponse> buscarTodosProjetos();

    ProjetoTarefasResponse alterarPorcentagem(Long idTarefa, AlterarPorcentagemRequest alterarPorcentagemRequest);

    ProjetoTarefasResponse adicionarTarefa(Long casoDeUso, TarefaRequest tarefaRequest);



}
