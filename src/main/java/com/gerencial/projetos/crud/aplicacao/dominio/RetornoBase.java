package com.gerencial.projetos.crud.aplicacao.dominio;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RetornoBase {

    private String codigo;
    private String erro;
    private String mensagem;

}
