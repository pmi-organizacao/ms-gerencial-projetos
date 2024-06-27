package com.gerencial.projetos.crud.aplicacao.exceptions.excessoes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ColaboradorExceptions extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;
}
