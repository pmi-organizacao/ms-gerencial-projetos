package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CasoDeUsoDetalheResponse {

    private Long id;

    private String nomeDaUc;

    private String descricaoDaUc;

    private Long idResponsavel;

    private String status;

    private String peso;

    private Integer ucEmAndamento;
    private Integer ucConcluidos;
    private Integer totalUcs;

}
