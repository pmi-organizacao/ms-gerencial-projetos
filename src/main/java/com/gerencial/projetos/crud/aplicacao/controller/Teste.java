package com.gerencial.projetos.crud.aplicacao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class Teste {

    @GetMapping
    public String teste() {
        return "TESTANDO API DE GERENCIAL";
    }

}
