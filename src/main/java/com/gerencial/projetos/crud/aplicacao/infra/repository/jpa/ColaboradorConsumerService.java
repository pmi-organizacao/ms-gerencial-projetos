package com.gerencial.projetos.crud.aplicacao.infra.repository.jpa;

import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import org.springframework.stereotype.Service;

@Service
public interface ColaboradorConsumerService {

    Colaborador buscarColaboradorPorId(Long id);

}
