package br.dev.codelabs;

import br.dev.codelabs.alunos.iAluno;
import br.dev.codelabs.avaliacoes.iAvaliacao;
import br.dev.codelabs.fabricas.iFabrica;
import br.dev.codelabs.matriculas.iMatricula;

public class Universidade {
	
	private final iFabrica persistencia;

	public Universidade(iFabrica persistencia) {
		this.persistencia = persistencia;
	}
	
	public iAluno novoAluno(String Cpf, String Nome, String DataRegistro) {
        return persistencia.novoAluno(Cpf, Nome, DataRegistro);
    }
	
	public iMatricula novaMatricula(iMatricula Matricula, String Tipo) {
		return persistencia.novaMatricula(Matricula, Tipo);
	}
	
	public iAvaliacao novaAvaliacaoTecnico(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro) {
        return persistencia.novaAvaliacaoTecnico(Curso, Materia, Aluno, Nota, DataRegistro);
    }
	
	public iAvaliacao checarAvaliacaoTecnico(Long Curso, Long Aluno) {
        return persistencia.checarAvaliacaoTecnico(Curso, Aluno);
    }
	
	public iAvaliacao novaAvaliacaoBacharelado(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro) {
        return persistencia.novaAvaliacaoBacharelado(Curso, Materia, Aluno, Nota, DataRegistro);
    }
	
	public iAvaliacao checarAvaliacaoBacharelado(Long Curso, Long Aluno) {
        return persistencia.checarAvaliacaoBacharelado(Curso, Aluno);
    }
	
	public iAvaliacao novaAvaliacaoMestrado(Long Curso, Long Materia, Long Aluno, String Conceito, String DataRegistro) {
        return persistencia.novaAvaliacaoMestrado(Curso, Materia, Aluno, Conceito, DataRegistro);
    }
	
	public iAvaliacao checarAvaliacaoMestrado(Long Curso, Long Aluno) {
        return persistencia.checarAvaliacaoMestrado(Curso, Aluno);
    }
}
