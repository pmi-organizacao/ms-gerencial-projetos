package com.gerencial.projetos.crud.aplicacao.dto.response;

import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.dominio.Tarefa;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CasosDeUsoResponse {

    private Long id;

    private String nomeDaUc;

    private String descricaoDaUc;

    private Long idResponsavel;

    private String status;

    private String peso;

    private List<TarefaResponse> tarefas = new ArrayList<>();


    public static CasosDeUsoResponse construirCasoDeUso(CasoDeUso casoDeUso){
        return CasosDeUsoResponse.builder()
                .id(casoDeUso.getId())
                .nomeDaUc(casoDeUso.getNomeDaUc())
                .descricaoDaUc(casoDeUso.getDescricaoDaUc())
                .status(casoDeUso.getStatus() * 100 + "%")
                .idResponsavel(casoDeUso.getIdResponsavel())
                .peso(casoDeUso.getPeso().toString())
                .tarefas(casoDeUso.getTarefas()
                        .stream()
                        .map(Tarefa::response)
                        .toList())
                .build();
    }

}
