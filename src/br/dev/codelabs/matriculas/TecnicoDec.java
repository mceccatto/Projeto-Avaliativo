package br.dev.codelabs.matriculas;

public class TecnicoDec extends Matricula {

	public TecnicoDec(iMatricula matricular) {
		super(matricular);
	}

	@Override
	public String matricular() {
		return matricular.matricular() + "ETAPA 2 - ALUNO MATRICULADO NO CURSO TÉCNICO\n";
	}

}