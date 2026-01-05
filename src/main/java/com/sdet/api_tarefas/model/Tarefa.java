package com.sdet.api_tarefas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity //crie uma tabela chamada 'Tarefa' no banco
@Data //lombock cria os Getters e Setters
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //gera um id unico pra cada tarefa
    private Long id;

    @NotBlank(message = "O titulo não pode estar em branco")
    @Size(min = 3, message = "O título deve ter pelo menos 3 caracteres")
    private String titulo;
    private boolean concluida;

}
