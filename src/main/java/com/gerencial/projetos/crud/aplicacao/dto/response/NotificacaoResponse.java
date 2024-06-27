package com.gerencial.projetos.crud.aplicacao.dto.response;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.ImportanciaEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NotificacaoResponse {

    private String mensagem;
    private ImportanciaEnum importancia;
    private String status;
    private LocalDate localDate;
    private CasosDeUsoResponse tarefa;

}
