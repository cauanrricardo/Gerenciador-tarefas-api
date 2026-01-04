package com.sdet.api_tarefas.service;

import com.sdet.api_tarefas.model.Tarefa;
import com.sdet.api_tarefas.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository repository;

    public List<Tarefa> listarTodas(){
        return  repository.findAll();
    }

    public Tarefa salvar(Tarefa tarefa){
        validarUpdate(tarefa);
        return repository.save(tarefa);
    }

    public void deletar(Long id){
        repository.deleteById(id);
    }

    public Tarefa update(Long id, Tarefa novaTarefa ){
        validarUpdate(novaTarefa);

        //tentar buscar a tarefa que ja esta no banco
        Tarefa tarefaExistente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Não encontrada"));

        //aruyalizar a taerfa com os dados novos
        tarefaExistente.setTitulo(novaTarefa.getTitulo());
        tarefaExistente.setConcluida(novaTarefa.isConcluida());

        //salvar a nova tarefa
        return  repository.save(novaTarefa);
    }
    private void validarUpdate(Tarefa novaTarefa){
        if(novaTarefa.getTitulo() == null || novaTarefa.getTitulo().isEmpty()){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO_VALIDACAO_VAZIO");
        }
        if (novaTarefa.getTitulo().length() <= 3) {
            System.out.println("DEBUG_SISTEMA: Detectou titulo curto!");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ERRO_VALIDACAO_CURTO");
        }

    }
}
