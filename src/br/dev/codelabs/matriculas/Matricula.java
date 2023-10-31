package br.dev.codelabs.matriculas;

public abstract class Matricula implements iMatricula{
	
	protected iMatricula matricular;
	
	public Matricula(iMatricula matricular) {
		this.matricular = matricular;
	}
	
}