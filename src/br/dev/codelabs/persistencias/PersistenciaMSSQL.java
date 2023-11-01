package br.dev.codelabs.persistencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.dev.codelabs.alunos.*;
import br.dev.codelabs.avaliacoes.*;
import br.dev.codelabs.conexoes.ConexaoMSSQL;
import br.dev.codelabs.fabricas.iFabrica;
import br.dev.codelabs.matriculas.*;

public class PersistenciaMSSQL implements iFabrica {
	
	@Override
	public iAluno novoAluno(String Cpf, String Nome, String DataRegistro) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		PreparedStatement stm = null;
		try {
			/*stm = conexao.prepareStatement("TRUNCATE TABLE dbo.alunos");
			stm.execute();*/
			stm = conexao.prepareStatement("TRUNCATE TABLE dbo.avaliacoes");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE dbo.cursos");
			stm.execute();
			stm = conexao.prepareStatement("INSERT INTO dbo.cursos (Nome) VALUES('Curso Profissionalizante em Auxiliar Administrativo'),('Engenaharia de Software'),('Mestrado em Administração')");
			stm.execute();
			stm = conexao.prepareStatement("TRUNCATE TABLE dbo.materias");
			stm.execute();
			stm = conexao.prepareStatement("INSERT INTO dbo.materias (Nome) VALUES('Materia TEC 01'),('Materia TEC 02'),('Materia TEC 03'),('Materia TEC 04'),('Materia TEC 05'),('Materia BAC 01'),('Materia BAC 02'),('Materia BAC 03'),('Materia BAC 04'),('Materia BAC 05'),('Materia MES 01'),('Materia MES 02'),('Materia MES 03'),('Materia MES 04'),('Materia MES 05')");
			stm.execute();
		} catch (SQLException e) {
			System.out.println(e);
		}
		Aluno aluno = new Aluno(Cpf, Nome, DataRegistro, conexao);
		System.out.println(aluno.registrar());
		ConexaoMSSQL.fecharConexao();
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
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Tecnico(Curso, Materia, Aluno, Nota, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoTecnico(Long Curso, Long Aluno) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Tecnico(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao novaAvaliacaoBacharelado(Long Curso, Long Materia, Long Aluno, double Nota, String DataRegistro) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Bacharelado(Curso, Materia, Aluno, Nota, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoBacharelado(Long Curso, Long Aluno) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Bacharelado(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao novaAvaliacaoMestrado(Long Curso, Long Materia, Long Aluno, String Conceito, String DataRegistro) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Mestrado(Curso, Materia, Aluno, Conceito, DataRegistro, conexao);
		System.out.println(avaliacao.registrar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

	@Override
	public iAvaliacao checarAvaliacaoMestrado(Long Curso, Long Aluno) {
		if(ConexaoMSSQL.getConexaoMSSQL() == null) {
			System.out.println(ConexaoMSSQL.statusConexao());
			return null;
		}
		Connection conexao = ConexaoMSSQL.getConexaoMSSQL();
		Avaliacao avaliacao = new Mestrado(Curso, Aluno, conexao);
		System.out.println(avaliacao.validar());
		ConexaoMSSQL.fecharConexao();
		return null;
	}

}
