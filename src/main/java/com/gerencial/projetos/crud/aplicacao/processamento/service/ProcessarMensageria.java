package com.gerencial.projetos.crud.aplicacao.processamento.service;

public abstract class ProcessarMensageria {

    protected ProcessarMensageria processarMensageria;

    public void excutarProcessos () {

        if (deveProcessar()) {
             efetuarEsteProcesso();
        }

        processarMensageria.excutarProcessos();
    }

    public abstract void efetuarEsteProcesso();
    public abstract Boolean deveProcessar();

}
