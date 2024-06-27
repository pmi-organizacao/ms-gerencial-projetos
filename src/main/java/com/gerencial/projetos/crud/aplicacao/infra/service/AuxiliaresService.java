package com.gerencial.projetos.crud.aplicacao.infra.service;


import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.request.ColaboradorResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoAuxResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ResumoProjetoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AuxiliaresService {


    Colaborador updateProjetoAddColaborador(Long idColaborador, String idProjeto);

    Colaborador updateOsDoProjetoDoColaborador(Long idColaborador, Long idOS);

    Colaborador updateUcDoColaborador(Long idColaborador, Long idCasoUso);

    Colaborador updateTarefaDoColaborador(Long idColaborador, Long idCasoUso);

    List<ColaboradorResponse> todosColaboradoresDe(String cargo);

    List<ProjetoAuxResponse> listaDeProjetos();

    ResumoProjetoResponse buscarResumoCasoDeUso(String id);

    ResumoProjetoResponse buscarResumoAtividade(Long id);


}
