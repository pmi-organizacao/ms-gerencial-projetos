package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalheTarefaResponse {

    private Long codigo;
    private String nome;
    private String status;
    private String responsavel;
    private String dificuldade;
    private Integer tarefaConcluidas;
    private Integer tarefaEmAndamento;
    private Integer totalTarefa;
}
