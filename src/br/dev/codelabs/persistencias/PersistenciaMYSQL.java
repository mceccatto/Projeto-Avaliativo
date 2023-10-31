package br.dev.codelabs.persistencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.dev.codelabs.alunos.Aluno;
import br.dev.codelabs.alunos.iAluno;
import br.dev.codelabs.avaliacoes.Avaliacao;
import br.dev.codelabs.avaliacoes.Tecnico;
import br.dev.codelabs.avaliacoes.Bacharelado;
import br.dev.codelabs.avaliacoes.Mestrado;
import br.dev.codelabs.avaliacoes.iAvaliacao;
import br.dev.codelabs.conexoes.ConexaoMYSQL;
import br.dev.codelabs.fabricas.iFabrica;
import br.dev.codelabs.matriculas.*;

public class PersistenciaMYSQL implements iFabrica {
	
	@Override
	public iAluno novoAluno(String Cpf, String Nome, String DataRegistro) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Aluno aluno = new Aluno(Cpf, Nome, DataRegistro, conexao);
		System.out.println(aluno.registrar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}
	
	@Override
	public iMatricula novaMatricula(iMatricula Matricula, String Tipo) {
		iMatricula matricula = Matricula;
		if(Tipo.equalsIgnoreCase("Tecnico")) {
			matricula = new TecnicoDec(matricula);
		}
		if(Tipo.equalsIgnoreCase("Bacharelado")) {
			matricula = new BachareladoDec(matricula);
		}
		if(Tipo.equalsIgnoreCase("Mestrado")) {
			matricula = new MestradoDec(matricula);
		}
		return matricula;
	}
	
	@Override
	public iAvaliacao novaAvaliacaoTecnico(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Tecnico(Curso, Materia, Aluno, Nota, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoTecnico(Long Curso, Long Aluno) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Tecnico(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao novaAvaliacaoBacharelado(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Bacharelado(Curso, Materia, Aluno, Nota, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoBacharelado(Long Curso, Long Aluno) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Bacharelado(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao novaAvaliacaoMestrado(Long Curso, Long Materia, Long Aluno, String Conceito, String DataRegistro) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Mestrado(Curso, Materia, Aluno, Conceito, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoMestrado(Long Curso, Long Aluno) {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		Avaliacao avaliacao = new Mestrado(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMYSQL.fecharConexao();
		return null;
	}

}
