package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalheOsResponse {

    private Long codigo;
    private String nome;
    private String status;
    private Integer sprintsConcluidas;
    private Integer sprintsEmAndamento;
    private Integer totalSprints;
    private Long idResponsavel;
    private String dificuldade;

}
