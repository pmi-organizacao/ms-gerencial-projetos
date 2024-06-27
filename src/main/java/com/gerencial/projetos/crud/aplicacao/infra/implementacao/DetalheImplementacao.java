package com.gerencial.projetos.crud.aplicacao.infra.implementacao;



import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.dominio.OrdemDeServico;
import com.gerencial.projetos.crud.aplicacao.dominio.Projeto;
import com.gerencial.projetos.crud.aplicacao.dominio.Sprint;
import com.gerencial.projetos.crud.aplicacao.dto.response.*;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.*;
import com.gerencial.projetos.crud.aplicacao.infra.service.DetalhamentoServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Service
public class DetalheImplementacao implements DetalhamentoServices {

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

    @Override
    public List<DetalheProjetoResponse> getDetalhamentoProjeto() {
        List<Projeto> projetos = projetoRepository.findAll();
        return projetos.stream().map(projeto -> {


        return DetalheProjetoResponse.builder()
                .codigo(projeto.getId())
                .nome(projeto.getNomeDoProjeto())
                .responsavel("implementando")
                .status(projeto.getStatus()+"%")
                .osConcluidas(projeto.tarefasConcluidas())
                .osEmAndamento(projeto.tarefasEmAndamento())
                .totalOs(projeto.getOrdemDeServicos().size())
                .build();
        }).toList();
    }


    @Override
    public List<DetalheOsResponse> getDetalhamentoOs(String nomeDoprojeto) {
        System.out.println(nomeDoprojeto);
        List<OrdemDeServico> ordemDeServicos = ordemDeServicoRepository
                .findByCodigoProjeto(nomeDoprojeto);

        if (Objects.isNull(ordemDeServicos)){
            System.out.println("vazio");

            return Arrays.asList();
        }

        List<DetalheOsResponse> detalheOsResponses = new ArrayList<>();
        ordemDeServicos.forEach(ordemDeServico -> {
            DetalheOsResponse detalheOsResponse = new DetalheOsResponse();
            detalheOsResponse.setCodigo(ordemDeServico.getId());
            detalheOsResponse.setNome(ordemDeServico.getNomeDaOS());
            detalheOsResponse.setDificuldade(ordemDeServico.getPeso().toString());
            detalheOsResponse.setIdResponsavel(ordemDeServico.getIdResponsavel());
            detalheOsResponse.setStatus(ordemDeServico.getStatus()+"%");
            detalheOsResponse.setSprintsEmAndamento(ordemDeServico.getSprints().stream().mapToInt(
                    sprint -> {
                        if (sprint.getStatus() > 0.0 && sprint.getStatus() < 100.00) {
                            return 1;
                        }
                        return 0;
                    }
            ).sum());

            detalheOsResponse.setSprintsConcluidas(ordemDeServico.getSprints().stream().mapToInt(
                    sprint -> {
                        if (sprint.getStatus().equals(100.00)) {
                            return 1;
                        }
                        return 0;
                    }
            ).sum());
            detalheOsResponse.setTotalSprints(ordemDeServico.getSprints().size());
            detalheOsResponses.add(detalheOsResponse);

        });


        return detalheOsResponses;

    }

    @Override
    public List<DetalheSprintResponse> getDetalhamentoSprint(Long id) {
        List<Sprint> sprints = sprintRepository.findByCodigoDaOs(id);
        System.out.println(sprints.size());
        List<DetalheSprintResponse> detalheOsResponse = new ArrayList<>();
        sprints.forEach(sprint -> {
            DetalheSprintResponse detalheSprintResponse = new DetalheSprintResponse();
            detalheSprintResponse.setCodigo(sprint.getId());
            detalheSprintResponse.setNome(sprint.getNomeDaSprint());
            detalheSprintResponse.setStatus(sprint.getStatus()+"%");
            detalheSprintResponse.setIdResponsavel(sprint.getIdResponsavel());
            detalheSprintResponse.setDificuldade(sprint.getPeso().toString());
            detalheSprintResponse.setSprintsEmAndamento(sprint.getCasoDeUsos().stream().mapToInt(uc -> {
                if (uc.getStatus() > 0.0 && uc.getStatus() < 1.0)
                    return 1;
                return 0;
            }).sum());
            detalheSprintResponse.setSprintsConcluidas(sprint.getCasoDeUsos().stream().mapToInt(uc -> {
                if (uc.getStatus().equals(1.00))
                    return 1;
                return 0;
            }).sum());
            detalheSprintResponse.setTotalSprints(sprint.getCasoDeUsos().size());

            detalheOsResponse.add(detalheSprintResponse);
        });
        return detalheOsResponse;
    }

    @Override
    public List<DetalheCasoDeUsoResponse> getDetalhamentoCasoDeUso(Long id) {
        List<CasoDeUso> casoDeUsos = casoDeUsoRepository.findByPeloIdSprint(id);
        List<DetalheCasoDeUsoResponse> detalheCasoDeUsoResponses = new ArrayList<>();
        casoDeUsos.forEach(casoDeUso -> {
            DetalheCasoDeUsoResponse response = new DetalheCasoDeUsoResponse();
            response.setCodigo(casoDeUso.getId());
            response.setNome(casoDeUso.getNomeDaUc());
            response.setStatus(casoDeUso.getStatus() * 100 +"%");
            response.setIdResponsavel(casoDeUso.getIdResponsavel());
            response.setDificuldade(casoDeUso.getPeso().toString());
            response.setCasosDeUsoEmAndamento(casoDeUso.getTarefas().stream().mapToInt(tarefa -> {
                if (tarefa.getStatus() > 0.0 && tarefa.getStatus() < 100.00)
                    return 1;
                return 0;
            }).sum());
            response.setCasosDeUsoConcluidas(casoDeUso.getTarefas().stream().mapToInt(tarefa -> {
                if (tarefa.getStatus().equals(100.00))
                    return 1;
                return 0;
            }).sum());
            response.setTotalcasosDeUso(casoDeUso.getTarefas().size());

            detalheCasoDeUsoResponses.add(response);
        });
        return detalheCasoDeUsoResponses;
    }


    private List<DetalheOsResponse> buscarDetalhesSprint(List<OrdemDeServico> ordemDeServicos) {

        return ordemDeServicos.stream().map(os -> {

            List<SprintDetalheResponse> sprintDetalheResponses = bucarDetalhaesSprints(os.getSprints());

            return DetalheOsResponse.builder()
                    .codigo(os.getId())
                    .nome(os.getNomeDaOS())
                    .status(os.getStatus()+ "%")
                    .dificuldade(os.getPeso().toString())
                    .idResponsavel(os.getIdResponsavel())
                    .sprintsEmAndamento(os.casosDeUsoEmAndamento())
                    .sprintsConcluidas(os.casosDeUsoConcluidos())
                    .totalSprints(os.getSprints().size())
                    .build();
        }).toList();
    }

    private List<SprintDetalheResponse> bucarDetalhaesSprints(List<Sprint> sprints) {
        return sprints
                .stream().map(sprint -> {

                    List<CasoDeUsoDetalheResponse> casosDeUss = buscarDetalheDoCasoDeUso(sprint.getCasoDeUsos());

                    return SprintDetalheResponse.builder()
                            .id(sprint.getId())
                            .nomeDaSprint(sprint.getNomeDaSprint())
                            .descricaoDaSprint(sprint.getDescricaoDaSprint())
                            .idResponsavel(sprint.getIdResponsavel())
                            .status(sprint.getStatus()+"%")
                            .peso(sprint.getPeso().toString())
                            .sprintEmAndamento(sprint.emAndamento())
                            .sprintConcluido(sprint.concluido())
                            .sprintTotal(sprint.getCasoDeUsos().size())
                            .build();
                }).toList();

    }

    private List<CasoDeUsoDetalheResponse> buscarDetalheDoCasoDeUso(List<CasoDeUso> casoDeUsos) {

           return casoDeUsos.stream().map(uc -> {

               return CasoDeUsoDetalheResponse.builder()
                       .id(uc.getId())
                       .nomeDaUc(uc.getNomeDaUc())
                       .descricaoDaUc(uc.getDescricaoDaUc())
                       .idResponsavel(uc.getIdResponsavel())
                       .status(uc.getStatus()+"%")
                       .peso(uc.getPeso().toString())
                       .ucEmAndamento(uc.emAndamento())
                       .ucConcluidos(uc.concluidos())
                       .totalUcs(uc.getTarefas().size())
                       .build();
           }).toList();

    }

}
