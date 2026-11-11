USE DIARIO;
GO

DROP TABLE IF EXISTS TAREFA;
GO

CREATE TABLE TAREFA (
                        ID_TAREFA BIGINT IDENTITY(1,1) NOT NULL,
                        TITULO VARCHAR(50) NOT NULL,
                        MATERIA VARCHAR(50),
                        DATA_ENTREGA DATETIME2(6),

                        STATUS_TAREFA VARCHAR(20) NOT NULL
                            CONSTRAINT CK_TAREFA_STATUS
                                CHECK (STATUS_TAREFA IN ('PENDENTE', 'EM_ANDAMENTO', 'CONCLUIDA')),

                        PRIORIDADE VARCHAR(10) NOT NULL
                            CONSTRAINT CK_TAREFA_PRIORIDADE
                                CHECK (PRIORIDADE IN ('BAIXA', 'MEDIA', 'ALTA')),

                        CONSTRAINT PK_TAREFA PRIMARY KEY (ID_TAREFA)
);
GO