package com.gerencial.projetos.crud.aplicacao.dto.response;

import com.gerencial.projetos.crud.aplicacao.dominio.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarefaResponse {

    private Long id;
    private String nomeTarefa;
    private String descricaoTarefa;
    private String responsavel;
    private String status;
    private String peso;

    public static TarefaResponse construirTarefaResponse(Tarefa tarefa) {
        return TarefaResponse.builder()
                .id(tarefa.getId())
                .nomeTarefa(tarefa.getNomeTarefa())
                .descricaoTarefa(tarefa.getDescricaoTarefa())
                .responsavel(tarefa.getResponsavel())
                .status(tarefa.getStatus() * 100 + "%")
                .peso(tarefa.getPeso().toString())
                .build();
    }

}
