package com.gerencial.projetos.crud.aplicacao.dominio;

import com.gerencial.projetos.crud.aplicacao.dto.enuns.ImportanciaEnum;
import com.gerencial.projetos.crud.aplicacao.dto.response.NotificacaoResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "TB_NOTIFICACOES")
public class Notificacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    private LocalDate data;

    @Enumerated(EnumType.STRING)
    private ImportanciaEnum importancia;

    @ManyToOne
    private CasoDeUso casoDeUso;

    private String status;

    public NotificacaoResponse response() {
        return NotificacaoResponse.builder()
                .mensagem(mensagem)
                .importancia(importancia)
                .status(status)
                .localDate(data)
                .tarefa(casoDeUso.response())
                .build();
    }
}
