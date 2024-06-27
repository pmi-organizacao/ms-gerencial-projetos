package com.gerencial.projetos.crud.aplicacao.dto.request;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.ImportanciaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificacaoRequest {

    private String mensagem;
    private ImportanciaEnum importancia;
    private Long idSubTarefa;

}
