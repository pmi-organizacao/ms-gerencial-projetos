package com.gerencial.projetos.crud.aplicacao.infra.implementacao;


import com.gerencial.projetos.crud.aplicacao.dominio.CasoDeUso;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.CasoDeUsoRepository;
import com.gerencial.projetos.crud.aplicacao.infra.repository.jpa.ColaboradorRepository;
import com.gerencial.projetos.crud.aplicacao.infra.service.FiltrosServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FiltrosServiceImplementacao implements FiltrosServices {

    @Autowired
    private CasoDeUsoRepository casoDeUsoRepository;

    @Autowired
    private ColaboradorRepository colaboradorRepository;

    @Override
    public List<CasoDeUso> projetosQueODesenvolvedorFazParte(String nome) {
        List<CasoDeUso> subTarefaResponseDevs = new ArrayList<>();
        List<CasoDeUso> atividades = casoDeUsoRepository.findByNome("%"+nome+"%");
        System.out.println(atividades.size());
        atividades.stream()
                .forEach(e -> {
                            subTarefaResponseDevs.add(e);
                });


        return atividades;
    }
}
