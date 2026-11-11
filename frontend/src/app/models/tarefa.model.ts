export type StatusTarefa = 'PENDENTE' | 'EM_ANDAMENTO' | 'CONCLUIDA';
export type Prioridade = 'BAIXA' | 'MEDIA' | 'ALTA';

export interface Tarefa {
  id?: number;
  titulo: string;
  materia: string;
  dataEntrega: string;
  status?: StatusTarefa;
  prioridade?: Prioridade;
}
