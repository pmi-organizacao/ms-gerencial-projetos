package com.gerencial.projetos.crud.aplicacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProjetoRequest {

    private String codigo;

    private String nomeDoProjeto;

    private String descricaoDoProjeto;

    private Long idResponsavel;


}
