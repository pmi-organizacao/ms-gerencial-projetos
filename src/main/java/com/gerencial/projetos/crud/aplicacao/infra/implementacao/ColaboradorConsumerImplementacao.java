package com.gerencial.projetos.crud.aplicacao.infra.implementacao;

import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.exceptions.excessoesTratadas.ColaboradorNaoEncontradoNoProjetoExceptions;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.ColaboradorConsumerRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.ColaboradorConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ColaboradorConsumerImplementacao implements ColaboradorConsumerService {

    @Autowired
    private ColaboradorConsumerRepository colaboradorConsumerRepository;

    @Override
    public Colaborador buscarColaboradorPorId(Long id) {
        Colaborador colaborador = colaboradorConsumerRepository.findById(id)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Colaborador não encontrado aqui no projeto."));
        return colaborador;
    }
}
