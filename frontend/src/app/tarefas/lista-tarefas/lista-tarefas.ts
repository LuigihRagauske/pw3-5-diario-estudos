import { Component, OnInit, inject, signal } from '@angular/core';
import { DatePipe } from '@angular/common';
import { Tarefa } from '../../models/tarefa.model';
import { TarefaService } from '../../services/tarefa.service';

@Component({
    selector: 'app-lista-tarefas',
    imports: [DatePipe],
    templateUrl: './lista-tarefas.html',
    styleUrl: './lista-tarefas.css'
})
export class ListaTarefas implements OnInit {

    private tarefaService = inject(TarefaService);

    tarefas = signal<Tarefa[]>([]);
    carregando = signal(false);
    erro = signal<string | null>(null);

    ngOnInit(): void {
        this.carregar();
    }

    carregar(): void {
        this.carregando.set(true);
        this.erro.set(null);

        this.tarefaService.listar().subscribe({
            next: (dados) => {
                this.tarefas.set(dados);
                this.carregando.set(false);
            },
            error: () => {
                this.erro.set('Não foi possível carregar as tarefas.');
                this.carregando.set(false);
            }
        });
    }

    concluir(tarefa: Tarefa): void {
        this.tarefaService.concluir(tarefa.id!).subscribe({
            next: (atualizada) => {
                this.tarefas.update(lista =>
                    lista.map(t => t.id === atualizada.id ? atualizada : t)
                );
            },
            error: () => this.erro.set('Não foi possível concluir a tarefa.')
        });
    }

    excluir(tarefa: Tarefa): void {
        if (!confirm(`Excluir a tarefa "${tarefa.titulo}"?`)) {
            return;
        }

        this.tarefaService.deletar(tarefa.id!).subscribe({
            next: () => {
                this.tarefas.update(lista => lista.filter(t => t.id !== tarefa.id));
            },
            error: () => this.erro.set('Não foi possível excluir a tarefa.')
        });
    }
}
