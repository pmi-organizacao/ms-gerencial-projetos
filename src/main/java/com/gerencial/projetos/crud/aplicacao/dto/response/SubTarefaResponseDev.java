package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubTarefaResponseDev {

    private Long codigo;
    private String nome;
    private String descricao;
    private String responsavel;
    private String status;
    private String dificuldade;

    private String codigoDaTarefa;

    private String codigoDaProjeto;

}
