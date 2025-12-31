package com.sdet.api_tarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity //crie uma tabela chamada 'Tarefa' no banco
@Data //lombock cria os Getters e Setters
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera um id unico pra cada tarefa
    private Long id;

    private String titulo;
    private boolean concluida;

}
