package com.gerencial.projetos.crud.aplicacao.dto.response;


import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
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
public class ProjetoTarefasResponse {

    private String codigo;
    private String nome;
    private String status;
    private String statusDSC;
    private Long responsavel;
    private String descricao;
    private List<OrdemDeServicoResponse> ordemDeServico = new ArrayList<>();
    private Integer totalDeOs;
    private Integer osEmAndamento;
    private Integer osConcluido;

    public static ProjetoTarefasResponse construirTarefaResponse(CasoDeUso casoDeUso){
        return casoDeUso
                .getSprint()
                .getOrdemDeServico()
                .getProjeto().responseTaf();
    }


}
