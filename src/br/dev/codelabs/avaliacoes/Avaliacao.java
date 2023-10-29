package br.dev.codelabs.avaliacoes;

import java.sql.Connection;

public abstract class Avaliacao implements iAvaliacao {
	
	protected String Tipo = this.getClass().getSimpleName();
	protected Long Curso;
	protected Long Materia;
	protected Long Aluno;
	protected String DataRegistro;
	protected Connection Conexao = null;
	
	public Avaliacao(Long Curso, Long Materia, Long Aluno, String DataRegistro, Connection Conexao) {
		this.Curso = Curso;
		this.Materia = Materia;
		this.Aluno = Aluno;
		this.DataRegistro = DataRegistro;
		this.Conexao = Conexao;
	}
	
	public Avaliacao(Long Curso, Long Aluno, Connection Conexao) {
		this.Curso = Curso;
		this.Aluno = Aluno;
		this.Conexao = Conexao;
	}
	
	public Avaliacao(String Tipo, Long Curso, Long Materia, Long Aluno) {
		this.Tipo = Tipo;
		this.Curso = Curso;
		this.Materia = Materia;
		this.Aluno = Aluno;
	}

	@Override
	public String registrar() {
		return null;
	}
	
	@Override
	public String validar() {
		return null;
	}

}
