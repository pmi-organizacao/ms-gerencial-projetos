package com.gerencial.projetos.crud.aplicacao.dto.request;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaRequest {


    private String nomeTarefa;
    private String descricaoTarefa;
    private String responsavel;
    private Peso peso;

}
