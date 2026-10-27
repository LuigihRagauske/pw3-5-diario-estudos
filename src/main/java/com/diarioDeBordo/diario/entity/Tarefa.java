package com.diarioDeBordo.diario.entity;

import com.diarioDeBordo.diario.enums.PrioridadeEnum;
import com.diarioDeBordo.diario.enums.StatusTarefaEnum;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "TAREFA")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TAREFA")
    private Long id;

    @Column(name = "TITULO")
    private String titulo;

    @Column(name = "MATERIA")
    private String materia;

    @Column(name = "DATA_ENTREGA")
    private LocalDateTime dataEntrega;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_TAREFA")
    private StatusTarefaEnum status;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORIDADE")
    private PrioridadeEnum prioridade;
}