package com.gerencial.projetos.crud.aplicacao.exceptions.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class DefaultErro {
    private int code;
    private String menssage;
}