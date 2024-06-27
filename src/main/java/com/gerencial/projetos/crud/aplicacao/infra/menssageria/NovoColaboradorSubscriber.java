package com.gerencial.projetos.crud.aplicacao.infra.menssageria;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gerencial.projetos.crud.aplicacao.dominio.Colaborador;
import com.gerencial.projetos.crud.aplicacao.dto.enuns.EStatusProjeto;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.ColaboradorConsumerRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.ColaboradorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class NovoColaboradorSubscriber {

    @Autowired
    private ColaboradorConsumerRepository colaboradorConsumerRepository;

    @RabbitListener(queues = "${mq.queues.adicionar-colaborador}")
    public void recebendoMensagens(@Payload String payload) {
        var mapper = new ObjectMapper();
        try {
            Colaborador colaborador = mapper.readValue(payload, Colaborador.class);
            colaborador.setStatus(EStatusProjeto.DISPONIVEL);
            System.out.println(colaborador.toString());
            colaboradorConsumerRepository.save(colaborador);
        } catch (JsonProcessingException e) {
            System.out.println("Erro ao consumir");
            throw new RuntimeException(e);
        }
    }

}
