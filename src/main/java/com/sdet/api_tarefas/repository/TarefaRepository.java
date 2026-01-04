package com.sdet.api_tarefas.repository;

import com.sdet.api_tarefas.model.Tarefa;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

@Registered
public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
