package br.dev.codelabs.matriculas;

public class MestradoDec extends Matricula{

	public MestradoDec(iMatricula matricular) {
		super(matricular);
	}
	
	@Override
	public String matricular() {
		return matricular.matricular() + "ETAPA 4 - ALUNO MATRICULADO NO MESTRADO\n";
	}
	
}