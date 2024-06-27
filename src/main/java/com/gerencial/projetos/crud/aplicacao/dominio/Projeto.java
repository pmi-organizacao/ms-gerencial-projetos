package com.gerencial.projetos.crud.aplicacao.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Slf4j
@Table(name = "TB_PROJETO")
public class Projeto {

    @Id
    @Column(unique = true)
    private String id;

    private String nomeDoProjeto;

    private String descricaoDoProjeto;

    private Long idResponsavel;

    @OneToMany(mappedBy = "projeto", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<OrdemDeServico> ordemDeServicos = new ArrayList<>();

    private Double status;

    private String statusDSC;


    private Integer quantidadeTotalTarefas() {

        if (Objects.isNull(ordemDeServicos)) {
            return  0;
        }else {
            return  this.ordemDeServicos.size();
        }

    }



    public ProjetoResponse response() {
        return ProjetoResponse.construirProjetoResponse(this);
    }

    public ProjetoTarefasResponse responseTaf() {
        return ProjetoResponse.construirProjetoResponseTaf(this);
    }


    public void adicionarTarefaAoProjeto(OrdemDeServico ordemDeServico) {
        this.ordemDeServicos.add(ordemDeServico);
    }

    public ProjetoTarefasResponse responseTafNull() {
        return ProjetoResponse.construirProjetoTafNull(this);
    }


    public Integer tarefasConcluidas () {
        if (Objects.isNull(ordemDeServicos)) {
            return 0;
        }

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<>();

        ordemDeServicos.forEach(tarefa -> {
            if (tarefa.getStatus().equals(100.0)){
                listOrdemDeServico.add(tarefa);
            }
        });
        return listOrdemDeServico.size();

    }

    public Integer tarefasEmAndamento () {
        if (Objects.isNull(ordemDeServicos)) {
            return 0;
        }

        List<OrdemDeServico> listOrdemDeServico = new ArrayList<>();

        ordemDeServicos.forEach(tarefa -> {
            if (tarefa.getStatus() > 0.0 && tarefa.getStatus() < 100.0){
                listOrdemDeServico.add(tarefa);
            }
        });


        return listOrdemDeServico.size();
    }



}
