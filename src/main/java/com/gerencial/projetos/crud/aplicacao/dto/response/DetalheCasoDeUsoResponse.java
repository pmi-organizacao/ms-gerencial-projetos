package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalheCasoDeUsoResponse {

    private Long codigo;
    private String nome;
    private String status;
    private Long idResponsavel;
    private String dificuldade;
    private Integer casosDeUsoConcluidas;
    private Integer casosDeUsoEmAndamento;
    private Integer totalcasosDeUso;
}
