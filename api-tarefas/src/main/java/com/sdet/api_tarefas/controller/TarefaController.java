package com.sdet.api_tarefas.controller;

import com.sdet.api_tarefas.model.Tarefa;
import com.sdet.api_tarefas.repository.TarefaRepository;
import com.sdet.api_tarefas.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //Define que esta classe recebe requisições HTTP (JSON)
@RequestMapping("/tarefas") //Define a URL base: http://localhost:8080/tarefas
public class TarefaController {

    @Autowired // "Injeta" o repositório para podermos usar aqui
    private TarefaService service;

    @GetMapping //Ativado quando você digita a URL no navegador GET(lISTAR)
    public List<Tarefa> listar(){
        return  service.listarTodas(); //Pede ao gerente: "Me dá tudo que tá no banco"
    }

    @PostMapping // ativado quando você ENVIA dados para a API  - POST(CRIAR)
    public Tarefa criar(@RequestBody Tarefa tarefa){
        return  service.salvar(tarefa);
    }

    @DeleteMapping("/{id}") //URL: localhost:8080/tarefas/1
    public void deletar(@PathVariable Long id){
        service.deletar(id);
    }
    @PutMapping("/{id}")
    public Tarefa update(@PathVariable Long id, @RequestBody Tarefa tarefa){
        return  service.update(id, tarefa);
    }

}
