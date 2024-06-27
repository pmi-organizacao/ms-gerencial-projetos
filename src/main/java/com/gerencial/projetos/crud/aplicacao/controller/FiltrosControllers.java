package com.gerencial.projetos.crud.aplicacao.controller;


import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.infra.service.FiltrosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("Filtros")
public class FiltrosControllers {

    @Autowired
    private FiltrosServices filtrosServices;

    @GetMapping("{nome}")
    public ResponseEntity<List<CasoDeUso>> buscarPprojetosDoDesenvolvedor(@PathVariable String nome) {
        return ResponseEntity.ok(filtrosServices.projetosQueODesenvolvedorFazParte(nome));
    }

}
