package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificacaoTarefaResponse {

    private Long id;
    private String nomeDoProjeto;
    private String dificuldadoDoProjeto;
    private List<NotificacaoResponse> notificacaoResponses;

}
