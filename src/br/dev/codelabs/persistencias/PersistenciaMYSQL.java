package br.dev.codelabs.persistencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.dev.codelabs.alunos.*;
import br.dev.codelabs.avaliacoes.*;
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
		PreparedStatement stm = null;
		try {
			stm = conexao.prepareStatement("SET foreign_key_checks = 0");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE alunos");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE avaliacoes");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE cursos");
			stm.execute();
			stm = conexao.prepareStatement("INSERT INTO cursos (Nome) VALUES('Curso Profissionalizante em Auxiliar Administrativo'),('Engenaharia de Software'),('Mestrado em Administração')");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE materias");
			stm.execute();
			stm = conexao.prepareStatement("INSERT INTO materias (Nome) VALUES('Materia TEC 01'),('Materia TEC 02'),('Materia TEC 03'),('Materia TEC 04'),('Materia TEC 05'),('Materia BAC 01'),('Materia BAC 02'),('Materia BAC 03'),('Materia BAC 04'),('Materia BAC 05'),('Materia MES 01'),('Materia MES 02'),('Materia MES 03'),('Materia MES 04'),('Materia MES 05')");
			stm.execute();
		} catch (SQLException e) {
			System.out.println(e);
		}
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
