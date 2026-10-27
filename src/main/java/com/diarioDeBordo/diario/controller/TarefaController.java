package com.diarioDeBordo.diario.controller;

import com.diarioDeBordo.diario.entity.Tarefa;
import com.diarioDeBordo.diario.service.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
@CrossOrigin
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping
    public List<Tarefa> listar() {
        return tarefaService.listar();
    }

    @GetMapping("/{id}")
    public Tarefa buscarPorId(@PathVariable Long id) {
        return tarefaService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return tarefaService.salvarTarefa(tarefa);
    }

    @PutMapping("/{id}")
    public Tarefa atualizar(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        return tarefaService.atualizar(id, tarefa);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tarefaService.deletar(id);
    }

    @PatchMapping("/{id}/concluir")
    public Tarefa concluir(@PathVariable Long id) {
        return tarefaService.concluir(id);
    }
}