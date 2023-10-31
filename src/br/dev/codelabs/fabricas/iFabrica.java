package br.dev.codelabs.fabricas;

import br.dev.codelabs.alunos.iAluno;
import br.dev.codelabs.avaliacoes.iAvaliacao;
import br.dev.codelabs.matriculas.iMatricula;

public interface iFabrica {
	iAluno novoAluno(String Cpf, String Nome, String DataRegistro);
	iMatricula novaMatricula(iMatricula Matricula, String Tipo);
	iAvaliacao novaAvaliacaoTecnico(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro);
	iAvaliacao checarAvaliacaoTecnico(Long Curso, Long Aluno);
	iAvaliacao novaAvaliacaoBacharelado(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro);
	iAvaliacao checarAvaliacaoBacharelado(Long Curso, Long Aluno);
	iAvaliacao novaAvaliacaoMestrado(Long Curso, Long Materia, Long Aluno, String Conceito, String DataRegistro);
	iAvaliacao checarAvaliacaoMestrado(Long Curso, Long Aluno);
}
