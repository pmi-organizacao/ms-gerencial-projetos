package com.gerencial.projetos.crud.aplicacao.dto.request;

import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EAcoesCosumers;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DadosTransferenciaUpdateColaboradorQueue {

    private Long idColaborador;
    @Enumerated(EnumType.STRING)
    private String idProjeto;
    private EAcoesCosumers processoExecutar;

    public static DadosTransferenciaUpdateColaboradorQueue transferAtualizarColaborador(Colaborador request, EAcoesCosumers processoExecutar) {
        return DadosTransferenciaUpdateColaboradorQueue.builder()
                .idColaborador(request.getId())
                .idProjeto(request.getProjeto().getId())
                .processoExecutar(processoExecutar)
                .build();
    }

}
