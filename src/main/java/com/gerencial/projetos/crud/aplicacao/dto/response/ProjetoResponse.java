package com.gerencial.projetos.crud.aplicacao.dto.response;


import com.gerencial.projetos.crud.aplicacao.dominio.OrdemDeServico;
import com.gerencial.projetos.crud.aplicacao.dominio.Projeto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProjetoResponse {

    private String codigo;
    private String nome;
    private String status;
    private String descricao;

    public static ProjetoResponse construirProjetoResponse(Projeto projeto){
        return ProjetoResponse.builder()
                .codigo(projeto.getId())
                .nome(projeto.getNomeDoProjeto())
                .status(projeto.getStatus() + "%")
                .descricao(projeto.getDescricaoDoProjeto())
                .build();
    }
    public static ProjetoTarefasResponse construirProjetoResponseTaf(Projeto projeto){
        return ProjetoTarefasResponse.builder()
                .codigo(projeto.getId())
                .nome(projeto.getNomeDoProjeto())
                .status(projeto.getStatus() + "%")
                .statusDSC(projeto.getStatusDSC())
                .responsavel(projeto.getIdResponsavel())
                .descricao(projeto.getDescricaoDoProjeto())
                .totalDeOs(projeto.getOrdemDeServicos().size())
                .osConcluido(projeto.tarefasConcluidas())
                .osEmAndamento(projeto.tarefasEmAndamento())
                .ordemDeServico(projeto.getOrdemDeServicos().stream().map(OrdemDeServico::responseCompleto).toList())
                .build();
    }

    public static ProjetoTarefasResponse construirProjetoTafNull(Projeto projeto) {
        return ProjetoTarefasResponse.builder()
                .codigo(projeto.getId())
                .status(projeto.getStatus() + "%")
                .nome(projeto.getNomeDoProjeto())
                .responsavel(projeto.getIdResponsavel())
                .descricao(projeto.getDescricaoDoProjeto())
                .ordemDeServico(projeto.getOrdemDeServicos().stream().map(OrdemDeServico::response).toList())
                .build();
    }


}
