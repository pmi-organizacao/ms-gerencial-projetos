package com.gerencial.projetos.crud.aplicacao.controller;


import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.request.ColaboradorResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoAuxResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ResumoProjetoResponse;
import com.gerencial.projetos.crud.aplicacao.infra.implementacao.ColaboradorServiceImplementacao;
import com.gerencial.projetos.crud.aplicacao.infra.service.AuxiliaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Auxiliar")
public class AuxiliaresColaboradores {

    @Autowired
    private AuxiliaresService auxiliaresService;

    @Autowired
    private ColaboradorServiceImplementacao colaboradorServiceImplementacao;

    @PostMapping("/update/projeto/add/colaborador/{codigoColaborador}")
    public Colaborador updateProjetoColaborador(@PathVariable Long codigoColaborador,
                                                @RequestParam String codigoProjetoDestino) {
        return auxiliaresService.updateProjetoAddColaborador(codigoColaborador, codigoProjetoDestino);
    }
    @PostMapping("/update/os/colaborador/{codidColaborador}")
    public Colaborador updateOsColaborador(@PathVariable Long codidColaborador,
                                           @RequestParam Long codigoIdOs) {
        return auxiliaresService.updateOsDoProjetoDoColaborador(codidColaborador, codigoIdOs);
    }
    @PostMapping("/update/uc/colaborador/{codigoColaborador}")
    public Colaborador updateUcColaborador(@PathVariable Long codigoColaborador,
                                               @RequestParam Long codigoIdCasoUso) {
        return auxiliaresService.updateUcDoColaborador(codigoColaborador, codigoIdCasoUso);
    }
    @PostMapping("/update/tarefa/colaborador/{codigoColaborador}")
    public Colaborador updateTarefaColaborador(@PathVariable Long codigoColaborador,
                                               @RequestParam Long codigoIdCasoUso) {
        return auxiliaresService.updateTarefaDoColaborador(codigoColaborador, codigoIdCasoUso);
    }

    @GetMapping("/buscar/todos/colaborador/{cargo}")
    public List<ColaboradorResponse> buscarTodosOsColaboradores(@PathVariable String cargo) {
        return auxiliaresService.todosColaboradoresDe(cargo);
    }

    @GetMapping("/buscar/todos/nomes-de-projeto")
    public List<ProjetoAuxResponse> buscarTodosOsNomesProjetos() {
        return auxiliaresService.listaDeProjetos();
    }

    @GetMapping("/buscar/resumo/por-os/{id}")
    public ResumoProjetoResponse buscarResumoOs(@PathVariable String id){
        return  auxiliaresService.buscarResumoCasoDeUso(id);
    }

    @GetMapping("/buscar/resumo-operacional/{id}")
    public ResumoProjetoResponse buscarResumoAtividades(@PathVariable Long id){
        return  auxiliaresService.buscarResumoAtividade(id);
    }
}
