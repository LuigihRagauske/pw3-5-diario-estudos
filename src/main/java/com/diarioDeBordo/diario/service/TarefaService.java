package com.diarioDeBordo.diario.service;

import com.diarioDeBordo.diario.entity.Tarefa;
import com.diarioDeBordo.diario.enums.PrioridadeEnum;
import com.diarioDeBordo.diario.enums.StatusTarefaEnum;
import com.diarioDeBordo.diario.repository.TarefaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    public Tarefa salvarTarefa(Tarefa tarefa) {
        long diasRestantes = ChronoUnit.DAYS.between(LocalDateTime.now(), tarefa.getDataEntrega());

        if (tarefa.getDataEntrega() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Data de entrega é obrigatória");
        }

        if (diasRestantes <= 2) {
            tarefa.setPrioridade(PrioridadeEnum.ALTA);
        } else if (diasRestantes <= 5) {
            tarefa.setPrioridade(PrioridadeEnum.MEDIA);
        } else {
            tarefa.setPrioridade(PrioridadeEnum.BAIXA);
        }

        if (tarefa.getStatus() == null) {
            tarefa.setStatus(StatusTarefaEnum.PENDENTE);
        }

        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listar() {
        return tarefaRepository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
    }

    public Tarefa atualizar(Long id, Tarefa dados) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setTitulo(dados.getTitulo());
        tarefa.setMateria(dados.getMateria());
        tarefa.setDataEntrega(dados.getDataEntrega());
        if (dados.getStatus() != null) {
            tarefa.setStatus(dados.getStatus());
        }
        return salvarTarefa(tarefa);
    }

    public void deletar(Long id) {
        tarefaRepository.delete(buscarPorId(id));
    }

    public Tarefa concluir(Long id) {
        Tarefa tarefa = buscarPorId(id);
        tarefa.setStatus(StatusTarefaEnum.CONCLUIDA);
        return tarefaRepository.save(tarefa);
    }
}