## Descrição

Projeto simples de API REST utilizando o framework Spring

## Script do banco de dados 

```bash
CREATE TABLE public.instrutor (
	id_instrutor serial4 NOT NULL,
	rg int4 NULL,
	nome varchar(45) NULL,
	nascimento timestamp NULL,
	titulacao int4 NULL,
	CONSTRAINT instrutor_pkey PRIMARY KEY (id_instrutor)
);

CREATE TABLE public.turma (
	id_turma serial4 NOT NULL,
	horario timestamp NULL,
	duracao int4 NULL,
	data_inicio timestamp NULL,
	data_fim timestamp NULL,
	id_instrutor int4 NULL,
	CONSTRAINT turma_pkey PRIMARY KEY (id_turma),
	CONSTRAINT turma_id_instrutor_fkey FOREIGN KEY (id_instrutor) REFERENCES public.instrutor(id_instrutor)
);
```

## Conceitos e recursos utilizados na aplicação

- Tratamento de recursividade infinita;
- Tratamento de exceções (de forma global e local);

## Configurando a API para execução

As credenciais para acesso ao banco de dados e o nome do contexto da API deverão ser alterados no arquivo application.properties

## Sobre

- Author - [Alexandre Paixão]

## Licença

GNU GPL
