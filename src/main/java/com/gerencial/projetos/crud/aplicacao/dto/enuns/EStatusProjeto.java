package com.gerencial.projetos.crud.aplicacao.dto.enuns;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EStatusProjeto {

    ALOCADO(1L, "Alocado em projeto"),
    DISPONIVEL(2L, "Não esta alocado em nenhum projeto"),
    EM_TREINAMENTO(3L, "Não esta alocado por que esta em treinamento.");

    private Long numerador;
    private String descricao;


}
