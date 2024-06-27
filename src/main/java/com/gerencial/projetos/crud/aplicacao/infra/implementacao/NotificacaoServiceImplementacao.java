package com.gerencial.projetos.crud.aplicacao.infra.implementacao;


import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.dominio.Notificacao;
import com.gerencial.projetos.crud.aplicacao.dominio.Tarefa;
import com.gerencial.projetos.crud.aplicacao.dto.request.NotificacaoRequest;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoTarefaResponse;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.CasoDeUsoRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.NotificacaoRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.TarefaRepository;
import com.gerencial.projetos.crud.aplicacao.infra.service.NotificacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificacaoServiceImplementacao implements NotificacaoService {

    @Autowired
    private NotificacaoRepository notificacaoRepository;

    @Autowired
    private CasoDeUsoRepository casoDeUsoRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Override
    public NotificacaoResponse addAnotacoes(NotificacaoRequest notificacaoRequest) {
        CasoDeUso casoDeUso = casoDeUsoRepository.findById(notificacaoRequest.getIdSubTarefa()).get();

        Notificacao notificacao = Notificacao.builder()
                .data(LocalDate.now())
                .mensagem(notificacaoRequest.getMensagem())
                .importancia(notificacaoRequest.getImportancia())
                .casoDeUso(casoDeUso)
                .status("NAO LIDO")
                .build();

        Notificacao notificacaoSalva = notificacaoRepository.save(notificacao);

        return notificacaoSalva.response();
    }

    @Override
    public NotificacaoTarefaResponse buscarNotificacaoTarefa(Long idTarefa) {
        Tarefa tarefa = tarefaRepository.findById(idTarefa).get();

        List<NotificacaoResponse> listaDasNotificacoes =
                tarefa.getNotificacoes().stream().map(Notificacao::response).toList();

        return NotificacaoTarefaResponse.builder()
                .nomeDoProjeto(tarefa.getNomeTarefa())
                .dificuldadoDoProjeto(tarefa.getPeso().toString())
                .id(tarefa.getId())
                .notificacaoResponses(listaDasNotificacoes)
                .build();
    }
}
