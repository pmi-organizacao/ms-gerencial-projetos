package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumoProjetoResponse {

    private Integer emAndamento;
    private Integer concluido;
    private Integer total;
    private String status;
    private String dificuldade;

}
