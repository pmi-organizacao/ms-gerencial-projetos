package com.gerencial.projetos.crud.aplicacao.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import com.gerencial.projetos.crud.aplicacao.dto.response.OrdemDeServicoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "TB_ORDEM_DE_SERVICO")
public class OrdemDeServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String codigoDaOS;

    private String nomeDaOS;

    private String descricaoDaOs;

    private Long idResponsavel;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ordemDeServico")
    @JsonIgnore
    private List<Sprint> sprints = new ArrayList<>();

    private Double status;

    @Enumerated(EnumType.STRING)
    private Peso peso;

    @ManyToOne
    private Projeto projeto;

    public OrdemDeServicoResponse response() {
        return OrdemDeServicoResponse.construirOrdemDeServico(this);
    }

    public OrdemDeServicoResponse responseCompleto() {
        return OrdemDeServicoResponse.construirOrdemDeServicoCompleto(this);
    }


    public void adicionarSprint(Sprint sprint) {
        this.sprints.add(sprint);
    }


    public Integer casosDeUsoConcluidos () {

        if (Objects.isNull(sprints)) {
            return 0;
        }

        List<CasoDeUso> listTarefa = new ArrayList<>();

        sprints.forEach(tarefa -> {
            if (tarefa.getStatus().equals(100.0)){
                listTarefa.add(tarefa.getCasoDeUsos().get(1));
            }
        });

        return listTarefa.size();
    }

    public Integer casosDeUsoEmAndamento () {

        if (Objects.isNull(sprints)) {
            System.out.println("dsadasdsadsa");

            return 0;
        }

        List<CasoDeUso> listTarefa = new ArrayList<>();

        sprints.forEach(tarefa -> {
            System.out.println("aiai " + tarefa.getStatus());
            if (tarefa.getStatus() > 0.0 && tarefa.getStatus() < 1.0){
                listTarefa.add(tarefa.getCasoDeUsos().get(1));
            }
        });

        return listTarefa.size();
    }



}
