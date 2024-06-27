package com.gerencial.projetos.crud.aplicacao.dto.request;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.Cargo;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EAcoesCosumers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequest {

    private Long idColaborador;
    private Long idProjeto;
    private Long idOs;
    private EAcoesCosumers acao;

}
