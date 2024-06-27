package com.gerencial.projetos.crud.aplicacao.infra.implementacao;


import com.gerencial.projetos.crud.aplicacao.dominio.*;
import com.gerencial.projetos.crud.aplicacao.dto.request.*;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.*;
import com.gerencial.projetos.crud.aplicacao.infra.service.ProjetoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ProjetoServiceImplementacao implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private OrdemDeServicoRepository ordemDeServicoRepository;

    @Autowired
    private CasoDeUsoRepository casoDeUsoRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private ColaboradorConsumerService colaboradorConsumerService;

    @Override
    public ProjetoTarefasResponse registrarNovoProjeto(ProjetoRequest projetoRequest) {
        log.info("[INICIO] - Criando novo projeto.");

        /*
        * Desenvolver os tratamento de erro para nome e descricao
        *
        * */

        log.info("[PROCESSANDO] - Verificando se o codigo do projeto ja existe.");
        projetoRepository.findAll().forEach((e) -> {
            if (e.getId().equals(projetoRequest.getCodigo())) {
                log.error("[ERRO] - Projeto " + projetoRequest.getCodigo() + " já existente.");
                throw new RuntimeException("Projeto já existente.");
            }

        });

        Projeto projeto = Projeto.builder()
                .id(projetoRequest.getCodigo())
                .nomeDoProjeto(projetoRequest.getNomeDoProjeto())
                .descricaoDoProjeto(projetoRequest.getDescricaoDoProjeto())
                .ordemDeServicos(Arrays.asList())
                .status(0.0)
                .statusDSC("Programado")
                .idResponsavel(colaboradorConsumerService.buscarColaboradorPorId(projetoRequest.getIdResponsavel()).getId())
                .build();
        log.info("[PROCESSANDO] - Instancia do Projeto criado para ser salvo.");
        Projeto projetoSalvo = projetoRepository.save(projeto);
        log.info("[FINALIZADO] - Projeto salvo no banco de dados.");

        return projetoSalvo.responseTaf();
    }

    @Override
    public ProjetoTarefasResponse adicionarOrdemDeServico(OrdemDeServicoRequest ordemDeServicoRequest, String codigoProjeto) {
        log.info("[INICIO] - Buscar projeto " + codigoProjeto +" para adicionar a tarefa.");
        Projeto projeto = projetoRepository.findById(codigoProjeto).get();

        if (Objects.nonNull(projeto)) {
            log.info("[PROCESSANDO] - Projeto " + codigoProjeto +" encontrado.");
        }

        OrdemDeServico ordemDeServico = OrdemDeServico.builder()
                .codigoDaOS(ordemDeServicoRequest.getCodigoDaOS())
                .nomeDaOS(ordemDeServicoRequest.getNomeDaOS())
                .descricaoDaOs(ordemDeServicoRequest.getDescricaoDaOs())
                .idResponsavel(ordemDeServicoRequest.getIdResponsavel())
                .sprints(Arrays.asList())
                .status(0.0)
                .peso(ordemDeServicoRequest.getPeso())
                .projeto(projeto)
                .build();

        log.info("[PROCESSANDO] - Salvando tarefa "+ ordemDeServicoRequest.getNomeDaOS() + " no banco de dados.");
        OrdemDeServico ordemDeServicoSalva = ordemDeServicoRepository.save(ordemDeServico);
        Projeto projetoPosTarefa = projetoRepository.findById(codigoProjeto).get();

        int somaDosPesos = projetoPosTarefa.getOrdemDeServicos().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum();

        Double somaDasPorcentagemComOsPesos = projetoPosTarefa.getOrdemDeServicos().stream()
                .mapToDouble((e) -> {
                    return e.getStatus() * e.getPeso().getPeso();
                }).sum();

        System.out.println((somaDasPorcentagemComOsPesos / somaDosPesos) * 100);
        projetoPosTarefa.setStatus((somaDasPorcentagemComOsPesos / somaDosPesos) * 100);


        if (Objects.nonNull(ordemDeServico.getSprints())) {
            log.info("[FINALIZADO -  Response] - Tarefa "+ ordemDeServicoRequest.getNomeDaOS() + " adicionada ao projeto.");
            return projetoRepository.save(projetoPosTarefa).responseTaf();
        }
        log.info("[FINALIZADO] - Tarefa "+ ordemDeServicoRequest.getNomeDaOS() + " adicionada ao projeto.");
        return projetoRepository.save(projetoPosTarefa).responseTafNull();

    }

    @Override
    public ProjetoTarefasResponse adicionarSprint(SprintRequest sprintRequest, Long idDaOs) {
        log.info("[INICIO] - Buscar OS de id: " + idDaOs +" para adicionar a Sprint.");
        OrdemDeServico ordemDeServico = ordemDeServicoRepository.findById(idDaOs).get() ;
        log.info("[PROCESSANDO] - OS: " + ordemDeServico.getNomeDaOS() +" encontrada.");


        log.info("[PROCESSANDO] - Criando instancia da Sprint: " + sprintRequest.getNomeDaSprint() +".");
        Sprint sprint = Sprint.builder()
                .nomeDaSprint(sprintRequest.getNomeDaSprint())
                .descricaoDaSprint(sprintRequest.getDescricaoDaSprint())
                .idResponsavel(sprintRequest.getIdResponsavel())
                .ordemDeServico(ordemDeServico)
                .casoDeUsos(Arrays.asList())
                .status(0.0)
                .peso(sprintRequest.getPeso())
                .build();

        log.info("[PROCESSANDO] - Adicionando a Sprint: " + sprintRequest.getNomeDaSprint() +" na os: "+ ordemDeServico.getNomeDaOS() +".");
        ordemDeServico.adicionarSprint(sprint);

        log.info("[PROCESSANDO] - SalvandO...... a OS: "+ ordemDeServico.getNomeDaOS() +".");
        OrdemDeServico ordemDeServicoSalva = ordemDeServicoRepository.save(ordemDeServico);

        log.info("[FINALIZADO] - Sprint: " + sprintRequest.getNomeDaSprint() +" Adicionado com sucesso.");
        return ordemDeServicoSalva.getProjeto().responseTaf();
    }

    @Override
    public ProjetoTarefasResponse adicionarCasoDeUso(CasoDeUsoRequest casoDeUsoRequest, Long idDaSprint) {
        log.info("[INICIO] - Buscar sprint " + idDaSprint +" para adicionar a atividade.");
        Sprint sprint = sprintRepository.findById(idDaSprint).get();
        log.info("[PROCESSANDO] - Sprint: " + sprint.getNomeDaSprint() +" encontrado.");


        log.info("[PROCESSANDO] - Criando o UC:  " + casoDeUsoRequest.getNomeDeCasoDeUso() +".");

        CasoDeUso casoDeUso = CasoDeUso.builder()
                .nomeDaUc(casoDeUsoRequest.getNomeDeCasoDeUso())
                .descricaoDaUc(casoDeUsoRequest.getDescricaoDoCasoDeUso())
                .idResponsavel(casoDeUsoRequest.getIdResponsavel())
                .sprint(sprint)
                .status(0.0)
                .peso(casoDeUsoRequest.getPeso())
                .build();

        log.info("[PROCESSANDO] - Adicionando a UC: " + casoDeUsoRequest.getNomeDeCasoDeUso() +" a Sprint: "+ sprint.getNomeDaSprint() +".");
        sprint.adicionarCasosDeUso(casoDeUso);

        log.info("[PROCESSANDO] - Salvando.... a Sprint: "+ sprint.getNomeDaSprint() +".");
        Sprint sprintSalva = sprintRepository.save(sprint);

        log.info("[FINALIZADO] - UC: " + casoDeUsoRequest.getNomeDeCasoDeUso() +" Adicionado com sucesso.");
        return sprintSalva.getOrdemDeServico().getProjeto().responseTaf();
    }

    @Override
    public List<ProjetoTarefasResponse> buscarTodosProjetos() {
        log.info("[INICIO] - Buscar todos projetos.");
        List<ProjetoTarefasResponse> list = projetoRepository.findAll().stream().map(Projeto::responseTaf).toList();
        log.info("[FINALIZADO] - Buscar todos projetos.");
        return list;
    }


    @Override
    public ProjetoTarefasResponse buscarProjetoPeloCodigo(String codigo) {
        log.info("[INICIO] - Buscar projeto "+ codigo + ".");
        return projetoRepository.findById(codigo).get().responseTaf();
    }


    @Override
    public ProjetoTarefasResponse alterarPorcentagem(Long idTarefa, AlterarPorcentagemRequest alterarPorcentagemRequest) {
        Double status = (double) alterarPorcentagemRequest.getStatus() / 100;

        log.info("[INICIO] - Alterar porcentagem da tarefa "+ idTarefa + ".");
        Tarefa tarefa = tarefaRepository.findById(idTarefa).get();

        log.info("[PROCESSANDO] - Inserir staus na tarefa "+ tarefa.getNomeTarefa() + ".");
        tarefa.setStatus(status);

        log.info("[PROCESSANDO] - Salvar tarefa com porcentagem de "+ tarefa.getStatus() + "%.");
        Tarefa tarefaSalva = tarefaRepository.save(tarefa);


        log.info("[PROCESSANDO] - Buscar caso de uso do id: "+ tarefaSalva.getCasoDeUso().getId() + ".");
        CasoDeUso casoDeUso = casoDeUsoRepository.findById(tarefaSalva.getCasoDeUso().getId()).get();


        log.info("[PROCESSANDO] - Inserir e salvar o status no Caso de uso "+ casoDeUso.getNomeDaUc() + ".");
        casoDeUso.setStatus(casoDeUso.getTarefas().stream().mapToDouble(e -> {
            return e.getStatus() * e.getPeso().getPeso();
        }).sum() / casoDeUso.getTarefas().stream().mapToInt(e -> {
            return e.getPeso().getPeso();
        }).sum());

        CasoDeUso casoDeUsoSalva = casoDeUsoRepository.save(casoDeUso);

        log.info("[PROCESSANDO] - Buscando as informações da SPRINT do caso de uso "+ casoDeUso.getNomeDaUc() + ".");
        Sprint sprint = sprintRepository.findById(casoDeUsoSalva.getSprint().getId()).get();


        log.info("[PROCESSANDO] - Calculos dos pesos, do caso de uso "+ casoDeUso.getNomeDaUc() + ".");
        int somaDosPesos = sprint.getCasoDeUsos().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum();

        double somaDasPorcentagemComOsPesos = sprint.getCasoDeUsos().stream()
                .mapToDouble((e) -> {
                    return e.getStatus() * e.getPeso().getPeso();
                }).sum();



        log.info("[PROCESSANDO] - Final dos Calculos e inserir o status na sprint: "+ sprint.getNomeDaSprint() + ".");
        sprint.setStatus((somaDasPorcentagemComOsPesos / somaDosPesos * 100));


        log.info("[PROCESSANDO] - Calculos do staTus e pesos da OS: "+ sprint.getNomeDaSprint() + ".");
        sprint.getOrdemDeServico().setStatus(sprint.getOrdemDeServico().getSprints().stream().mapToDouble((e) -> {
            return e.getStatus() * e.getPeso().getPeso();
        }).sum() / sprint.getOrdemDeServico().getSprints().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum());
        log.info("[PROCESSANDO] - Final do calculo do staTus da OS: "+ sprint.getNomeDaSprint() + ".");


        Projeto projeto = sprintRepository.save(sprint).getOrdemDeServico().getProjeto();
        log.info("[PROCESSANDO] - Sprint: "+ sprint.getNomeDaSprint() + " foi salvo com sucesso. / " +
                "E obtendo o projeto "+ projeto.getNomeDoProjeto() +".");

        /*
        System.out.println(projeto.getOrdemDeServicos().stream().map(OrdemDeServico::getStatus).toList());
        System.out.println(projeto.getOrdemDeServicos().stream().mapToDouble(e -> {
             return e.getStatus() * e.getPeso().getPeso();
        }).sum() / projeto.getOrdemDeServicos().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum() * 100);
        */

        log.info("[PROCESSANDO] - Calculando pesos das OSs do projeto: "+ projeto.getNomeDoProjeto() + ".");
        /*int somaDosPesosProjeto = projeto.getOrdemDeServicos().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum();

        double somaDasPorcentagemComOsPesosProjeto = projeto.getOrdemDeServicos().stream()
                .mapToDouble((e) -> {
                    return e.getStatus() * e.getPeso().getPeso();
                }).sum();*/


        projeto.setStatus(projeto.getOrdemDeServicos().stream().mapToDouble(e -> {
            return e.getStatus() * e.getPeso().getPeso();
        }).sum() / projeto.getOrdemDeServicos().stream().mapToInt((e) -> {
            return e.getPeso().getPeso();
        }).sum());
        log.info("[PROCESSANDO] - Final do Calculo e inserção dos status: "+ projeto.getNomeDoProjeto() + ".");


        Projeto projetoSalvo = projetoRepository.save(projeto);
        log.info("[FINALIZADO] - Pporcentagem do projeto "+ projetoSalvo.getStatus() + ".");


        return projetoSalvo.responseTaf();
    }

    @Override
    public ProjetoTarefasResponse adicionarTarefa(Long casoDeUso, TarefaRequest tarefaRequest) {

        CasoDeUso casoDeUsoRecuperado = casoDeUsoRepository.findById(casoDeUso).get();

        Tarefa tarefa = Tarefa.builder()
                .nomeTarefa(tarefaRequest.getNomeTarefa())
                .descricaoTarefa(tarefaRequest.getDescricaoTarefa())
                .responsavel(tarefaRequest.getResponsavel())
                .status(0.0)
                .peso(tarefaRequest.getPeso())
                .casoDeUso(casoDeUsoRecuperado)
                .build();

        Tarefa tarefaSalvo = tarefaRepository.save(tarefa);

        return tarefaSalvo.responseBase();
    }

}
