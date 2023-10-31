package br.dev.codelabs.matriculas;

public class BachareladoDec extends Matricula{

	public BachareladoDec(iMatricula matricular) {
		super(matricular);
	}
	
	@Override
	public String matricular() {
		return matricular.matricular() + "ETAPA 3 - ALUNO MATRICULADO NO BACHARELADO\n";
	}

}