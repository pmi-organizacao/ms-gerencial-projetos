package com.gerencial.projetos.crud.aplicacao.dto.enuns;

public enum Peso {

    FACIL(1),
    MODERADO(2),
    COMPLEXO(3),
    MUITO_COMPLEXO(4);

    private Integer peso;

    Peso(Integer peso) {
        this.peso = peso;
    }

    public Integer getPeso() {
        return peso;
    }
}
