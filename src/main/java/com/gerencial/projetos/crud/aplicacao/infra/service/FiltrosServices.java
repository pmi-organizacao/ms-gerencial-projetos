package com.gerencial.projetos.crud.aplicacao.infra.service;

import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FiltrosServices {

    List<CasoDeUso> projetosQueODesenvolvedorFazParte(String nome);
}
