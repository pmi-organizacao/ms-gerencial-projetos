package com.gerencial.projetos.crud.aplicacao.dominio;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import com.gerencial.projetos.crud.aplicacao.dto.response.ProjetoTarefasResponse;
import com.gerencial.projetos.crud.aplicacao.dto.response.TarefaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "TB_TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeTarefa;

    private String descricaoTarefa;

    private String responsavel;

    private Double status;

    @Enumerated(EnumType.STRING)
    private Peso peso;

    @ManyToOne
    private CasoDeUso casoDeUso;

    @OneToMany(mappedBy = "casoDeUso", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Notificacao> notificacoes;

    public TarefaResponse response() {
        return TarefaResponse.construirTarefaResponse(this);

    }

    public ProjetoTarefasResponse responseBase() {
        return ProjetoTarefasResponse.construirTarefaResponse(casoDeUso);
    }
}
