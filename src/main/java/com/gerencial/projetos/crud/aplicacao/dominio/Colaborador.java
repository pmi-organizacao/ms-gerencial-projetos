package com.gerencial.projetos.crud.aplicacao.dominio;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.EStatusProjeto;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TB_COLABORADOR_PROJETO")
public class Colaborador {

    @Id
    private Long id;
    private String nomeCompleto;
    private String nomeCargo;
    private EStatusProjeto status;
    @ManyToOne
    @JoinColumn(name = "idProjeto")
    private Projeto projeto;
    @ManyToOne
    @JoinColumn(name = "idOrdemDeServico")
    private OrdemDeServico ordemDeServico;
    @ManyToOne
    @JoinColumn(name = "idCasoDeUso")
    private CasoDeUso casoDeUso;
    @ManyToOne
    @JoinColumn(name = "idTarefa")
    private Tarefa tarefa;
}
