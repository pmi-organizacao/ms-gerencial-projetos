package com.gerencial.projetos.crud.aplicacao.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class mqConfig {

    @Value("${mq.queues.atualizar-colaborador}")
    private String atualziarColaborador;

    @Bean
    public Queue queueUpdateColaborador() {
        return new Queue(atualziarColaborador, true);
    }
}
