package com.gerencial.projetos.crud.aplicacao.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AlterarPorcentagemRequest {

    private Integer status;

}
