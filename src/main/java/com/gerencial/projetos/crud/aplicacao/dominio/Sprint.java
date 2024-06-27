package com.gerencial.projetos.crud.aplicacao.dominio;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.Peso;
import com.gerencial.projetos.crud.aplicacao.dto.response.SprintResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "TB_SPRINT")
public class Sprint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeDaSprint;

    private String descricaoDaSprint;

    private Long idResponsavel;

    private Double status;

    @Enumerated(EnumType.STRING)
    private Peso peso;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sprint")
    @JsonIgnore
    private List<CasoDeUso> casoDeUsos = new ArrayList<>();


    @ManyToOne
    private OrdemDeServico ordemDeServico;

    public SprintResponse response() {
        return SprintResponse.construirSprint(this);
    }


    public void adicionarCasosDeUso(CasoDeUso casoDeUso) {
        this.casoDeUsos.add(casoDeUso);
    }

    public Integer emAndamento() {

        if (Objects.isNull(casoDeUsos)) {
            return 0;
        }
        List<CasoDeUso> litasCasoDeUsos = new ArrayList<>();

        casoDeUsos.forEach(uc -> {
            if (uc.getStatus() > 0.0 && uc.getStatus() < 1.0){
                litasCasoDeUsos.add(uc);
            }
        });
        return litasCasoDeUsos.size();
    }

    public Integer concluido() {

        if (Objects.isNull(casoDeUsos)) {
            return 0;
        }
        List<CasoDeUso> litasCasoDeUsos = new ArrayList<>();

        casoDeUsos.forEach(uc -> {
            if (uc.getStatus().equals(1.0)){
                litasCasoDeUsos.add(uc);
            }
        });
        return litasCasoDeUsos.size();
    }
}
