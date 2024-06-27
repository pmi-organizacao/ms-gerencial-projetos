package com.gerencial.projetos.crud.aplicacao.dto.request;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdemDeServicoRequest {

    private String codigoDaOS;

    private String nomeDaOS;

    private String descricaoDaOs;

    private Long idResponsavel;

    private Peso peso;


}
