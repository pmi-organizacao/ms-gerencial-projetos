package com.gerencial.projetos.crud.aplicacao.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DetalheProjetoResponse {

    private String codigo;
    private String nome;
    private String status;
    private String responsavel;
    private Integer osConcluidas;
    private Integer osEmAndamento;
    private Integer totalOs;


}
