package com.gerencial.projetos.crud.aplicacao.dto.response;

import com.gerencial.projetos.crud.aplicacao.dominio.OrdemDeServico;
import com.gerencial.projetos.crud.aplicacao.dominio.Sprint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrdemDeServicoResponse {

    private String codigo;
    private String nome;
    private String descricao;
    private Long   idResponsavel;
    private String status;
    private String dificuldade;
    private List<SprintResponse> sprints = new ArrayList<>();


    public  static OrdemDeServicoResponse construirOrdemDeServico(OrdemDeServico ordemDeServico){
        return OrdemDeServicoResponse.builder()
                .nome(ordemDeServico.getNomeDaOS())
                .codigo(ordemDeServico.getCodigoDaOS())
                .status(ordemDeServico.getStatus() * 100 + "%")
                .dificuldade(ordemDeServico.getPeso().toString())
                .idResponsavel(ordemDeServico.getIdResponsavel())
                .sprints(Arrays.asList())
                .descricao(ordemDeServico.getDescricaoDaOs())
                .build();
    }
    public  static OrdemDeServicoResponse construirOrdemDeServicoCompleto(OrdemDeServico ordemDeServico){
        return OrdemDeServicoResponse.builder()
                .nome(ordemDeServico.getNomeDaOS())
                .codigo(ordemDeServico.getCodigoDaOS())
                .status(ordemDeServico.getStatus() + "%")
                .dificuldade(ordemDeServico.getPeso().toString())
                .idResponsavel(ordemDeServico.getIdResponsavel())
                .descricao(ordemDeServico.getDescricaoDaOs())
                .sprints(ordemDeServico.getSprints().stream().map(Sprint::response).toList())
                .build();
    }

}
