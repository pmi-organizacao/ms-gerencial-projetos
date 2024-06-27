package com.gerencial.projetos.crud.aplicacao.exceptions.handler;

import com.gerencial.projetos.crud.aplicacao.exceptions.dto.StandardError;
import com.gerencial.projetos.crud.aplicacao.exceptions.excessoesTratadas.ColaboradorNaoEncontradoNoProjetoExceptions;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@ControllerAdvice
public class RestTemplateHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ColaboradorNaoEncontradoNoProjetoExceptions.class)
    public ResponseEntity<StandardError> handlerColaboradorNaoEncontradoNoProjetoExceptions (ColaboradorNaoEncontradoNoProjetoExceptions e, HttpServletRequest request){
        StandardError erro = StandardError.builder()
                .timestamp(Instant.now())
                .status(HttpStatus.NOT_FOUND.value())
                .error("Colaborador não encontrado")
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

    }
}
