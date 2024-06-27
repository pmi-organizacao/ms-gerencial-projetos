package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SprintDetalheResponse {

    private Long id;

    private String nomeDaSprint;

    private String descricaoDaSprint;

    private Long idResponsavel;

    private String status;

    private String peso;

    private Integer sprintEmAndamento;
    private Integer sprintConcluido;
    private Integer sprintTotal;


}
