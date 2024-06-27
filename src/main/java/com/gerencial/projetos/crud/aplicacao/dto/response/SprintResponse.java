package com.gerencial.projetos.crud.aplicacao.dto.response;

import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.dominio.Sprint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SprintResponse {

    private Long codigo;
    private String nomeDaSprint;
    private String descricaoDaSprint;
    private Long idResponsavel;
    private String status;
    private String dificuldade;
    private List<CasosDeUsoResponse> casoDeUsos = new ArrayList<>();

    public static SprintResponse construirSprint(Sprint sprint){
        return SprintResponse.builder()
                .codigo(sprint.getId())
                .nomeDaSprint(sprint.getNomeDaSprint())
                .descricaoDaSprint(sprint.getDescricaoDaSprint())
                .idResponsavel(sprint.getIdResponsavel())
                .status(sprint.getStatus() + "%")
                .casoDeUsos(sprint.getCasoDeUsos().stream().map(CasoDeUso::response).toList())
                .dificuldade(sprint.getPeso().toString())
                .build();
    };

}
