package com.gerencial.projetos.crud.aplicacao.dto.request;

import lombok.*;

@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorResponse{

    private Long id;
    private String nomeCompleto;
    private String nomeCargo;
    private Long status;
    private Long os;
    private Long uc;
    private Long tarefa;


}
