package com.gerencial.projetos.crud.aplicacao.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import com.gerencial.projetos.crud.aplicacao.dto.response.CasosDeUsoResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.SubTarefaResponseDev;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_CASOS_DE_USO")
public class CasoDeUso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nomeDaUc;

    private String descricaoDaUc;

    private Long idResponsavel;

    private Double status;

    @Enumerated(EnumType.STRING)
    private Peso peso;

    @ManyToOne
    @JsonIgnore
    private Sprint sprint;

    @OneToMany(mappedBy = "casoDeUso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Tarefa> tarefas;


    public CasosDeUsoResponse response() {
        return CasosDeUsoResponse.construirCasoDeUso(this);
    }

//    public SubTarefaResponseDev responseDev() {
//        return SubTarefaResponseDev.builder()
//                .codigo(this.id)
//                .nome(this.nomeDaUc)
//                .descricao(this.descricaoDaUc)
//                .responsavel(this.idResponsavel)
//                .status(this.status * 100 + "%")
//                .dificuldade(this.peso.toString())
//                .codigoDaTarefa(this.sprint.getNomeDaSprint())
//                .codigoDaProjeto(this.sprint.getOrdemDeServico().getProjeto().getId())
//                .build();
//    }


    public Integer emAndamento() {

        if (Objects.isNull(tarefas)) {
            return 0;
        }

        List<Tarefa> listTarefa = new ArrayList<>();

        tarefas.forEach(tarefa -> {
            if (tarefa.getStatus() > 0.0 && tarefa.getStatus() < 1.0){
                listTarefa.add(tarefa);
            }
        });

        return listTarefa.size();
    }

    public Integer concluidos() {
        if (Objects.isNull(tarefas)) {
            return 0;
        }

        List<Tarefa> listTarefa = new ArrayList<>();

        tarefas.forEach(tarefa -> {
            if (tarefa.getStatus().equals(1.0)){
                listTarefa.add(tarefa);
            }
        });

        return listTarefa.size();
    }
}
