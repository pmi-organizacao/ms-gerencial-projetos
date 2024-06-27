package com.gerencial.projetos.crud.aplicacao.exceptions.excessoesTratadas;

import com.gerencial.projetos.crud.aplicacao.exceptions.excessoes.ColaboradorExceptions;

public class ColaboradorNaoEncontradoNoProjetoExceptions extends ColaboradorExceptions {
    private static final long serialVersionUID = 1L;

    public ColaboradorNaoEncontradoNoProjetoExceptions(String message) {
        super(message);
    }
}
