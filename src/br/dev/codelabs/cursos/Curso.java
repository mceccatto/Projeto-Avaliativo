package br.dev.codelabs.cursos;

import java.sql.Connection;

public abstract class Curso implements iCurso {
	
	protected String Tipo = this.getClass().getSimpleName();
	protected Long Curso;
	protected Long Materia;
	protected Long Aluno;
	protected String DataRegistro;
	protected Connection Conexao = null;

	@Override
	public String registrar() {
		return null;
	}

}
