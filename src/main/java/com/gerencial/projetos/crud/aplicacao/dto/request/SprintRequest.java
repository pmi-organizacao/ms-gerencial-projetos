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
public class SprintRequest {

    private String nomeDaSprint;

    private String descricaoDaSprint;

    private Long idResponsavel;

    private Peso peso;


}
