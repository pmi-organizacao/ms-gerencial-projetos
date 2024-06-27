package com.gerencial.projetos.crud.aplicacao.infra.implementacao;

import com.gerencial.projetos.crud.aplicacao.dominio.*;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EAcoesCosumers;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EStatusProjeto;
import com.gerencial.projetos.crud.aplicacao.dto.request.ColaboradorResponse;
import com.gerencial.projetos.crud.aplicacao.dto.request.DadosTransferenciaUpdateColaboradorQueue;
import com.gerencial.projetos.crud.aplicacao.dto.request.AtualizarOsColaboradorDTO;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoAuxResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ResumoProjetoResponse;
import com.gerencial.projetos.crud.aplicacao.exceptions.excessoesTratadas.ColaboradorNaoEncontradoNoProjetoExceptions;
import com.gerencial.projetos.crud.aplicacao.infra.menssageria.AtualizarColaboradorQueue;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jdbc.AuxiliaresRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.*;
import com.gerencial.projetos.crud.aplicacao.infra.service.AuxiliaresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColaboradorServiceImplementacao implements AuxiliaresService {

    @Autowired
    private ColaboradorConsumerRepository colaboradorRepository;

    @Autowired
    private AuxiliaresRepository auxiliaresRepository;

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private AtualizarColaboradorQueue atualizarColaboradorQueue;

    @Autowired
    private ColaboradorConsumerImplementacao colaboradorConsumerImplementacao;

    @Autowired
    private ProjetoServiceImplementacao projetoServiceImplementacao;
    @Autowired
    private TarefaRepository tarefaRepository;
    @Autowired
    private CasoDeUsoRepository casoDeUsoRepository;

    public Colaborador updateProjetoAddColaborador(Long idColaborador, String idProjeto) {
        Colaborador colaborador = colaboradorConsumerImplementacao.buscarColaboradorPorId(idColaborador);
        Projeto projeto = projetoRepository.findById(idProjeto)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Projeto não encontrado."));
        colaborador.setStatus(EStatusProjeto.DISPONIVEL);
        colaborador.setProjeto(projeto);
        Colaborador colaboradorSalvo = colaboradorRepository.save(colaborador);
        atualizarColaboradorQueue
                .atualizandoColaborador(DadosTransferenciaUpdateColaboradorQueue
                        .transferAtualizarColaborador(colaboradorSalvo, EAcoesCosumers.ATUALIZAR));
        return colaboradorSalvo;
    }

    @Override
    public Colaborador updateOsDoProjetoDoColaborador(Long idColaborador, Long idOS) {
        Colaborador colaborador = colaboradorRepository.findById(idColaborador)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Colaborador não encontrado."));
        OrdemDeServico ordemDeServico = ordemDeServicoRepository.findById(idOS)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Ordem de serviço não encontrado."));
        colaborador.setStatus(EStatusProjeto.ALOCADO);
        colaborador.setOrdemDeServico(ordemDeServico);

//        atualizarColaboradorQueue.atualizandoColaborador(DadosTransferenciaUpdateColaboradorQueue.transferAtualizarColaborador(request, EAcoesCosumers.ATUALIZAR));
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public Colaborador updateUcDoColaborador(Long idColaborador, Long idCasoUso) {
        Colaborador colaborador = colaboradorConsumerImplementacao.buscarColaboradorPorId(idColaborador);
        CasoDeUso casoDeUso = casoDeUsoRepository.findById(idCasoUso)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Caso de uso não encontrado."));
        colaborador.setCasoDeUso(casoDeUso);
        return colaboradorRepository.save(colaborador);
    }

    @Override
    public Colaborador updateTarefaDoColaborador(Long idColaborador, Long idTarefa) {
        Colaborador colaborador = colaboradorConsumerImplementacao.buscarColaboradorPorId(idColaborador);
        Tarefa tarefa = tarefaRepository.findById(idTarefa)
                .orElseThrow(() -> new ColaboradorNaoEncontradoNoProjetoExceptions("Tarefa não encontrado."));
        colaborador.setTarefa(tarefa);
        return colaboradorRepository.save(colaborador);
    }


    @Override
    public List<ColaboradorResponse> todosColaboradoresDe(String cargo) {
        return auxiliaresRepository.buscarColaboradoresDe(cargo);
    }

    @Override
    public List<ProjetoAuxResponse> listaDeProjetos() {
        return auxiliaresRepository.buscarNomeDosProjetos();
    }

    @Override
    public ResumoProjetoResponse buscarResumoCasoDeUso(String id) {
        Projeto projeto = projetoRepository.findById(id).get();
        return ResumoProjetoResponse.builder()
                .total(projeto.getOrdemDeServicos().size())
                .emAndamento(projeto.tarefasEmAndamento())
                .concluido(projeto.tarefasConcluidas())
                .status(String.format("%.0f", projeto.getStatus()))
                .build();
    }

    @Override
    public ResumoProjetoResponse buscarResumoAtividade(Long id) {
        OrdemDeServico ordemDeServico = ordemDeServicoRepository.findById(id).get();
        ResumoProjetoResponse response = ResumoProjetoResponse.builder()
                .total(ordemDeServico.getSprints().size())
                .emAndamento(ordemDeServico.casosDeUsoEmAndamento())
                .concluido(ordemDeServico.casosDeUsoConcluidos())
                .status(String.format("%.0f", ordemDeServico.getStatus() * 100))
                .dificuldade(ordemDeServico.getPeso().toString())
                .build();
        return response;
    }

}
