package br.dev.codelabs.persistencias;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import br.dev.codelabs.conexoes.ConexaoMYSQL;

public class Limpar {
	public static void limpar() {
		if(ConexaoMYSQL.getConexaoMYSQL() == null) {
			System.out.println(ConexaoMYSQL.statusConexao());
		}
		Connection conexao = ConexaoMYSQL.getConexaoMYSQL();
		PreparedStatement stm = null;
		try {
		stm = conexao.prepareStatement("TRUNCATE TABLE alunos");
		stm.execute();
		stm = conexao.prepareStatement("TRUNCATE TABLE avaliacoes");
		stm.execute();
		stm = conexao.prepareStatement("TRUNCATE TABLE cursos");
		stm.execute();
		stm = conexao.prepareStatement("INSERT INTO cursos (Id, Nome) VALUES(1, 'Curso Profissionalizante em Auxiliar Administrativo'),(2, 'Engenaharia de Software'),(3, 'Mestrado em Administração')");
		stm.execute();
		stm = conexao.prepareStatement("TRUNCATE TABLE materias");
		stm.execute();
		stm = conexao.prepareStatement("INSERT INTO materias (Id, Nome) VALUES(1, 'Materia TEC 01'),(2, 'Materia TEC 02'),(3, 'Materia TEC 03'),(4, 'Materia TEC 04'),(5, 'Materia TEC 05'),(6, 'Materia BAC 01'),(7, 'Materia BAC 02'),(8, 'Materia BAC 03'),(9, 'Materia BAC 04'),(10, 'Materia BAC 05'),(11, 'Materia MES 01'),(12, 'Materia MES 02'),(13, 'Materia MES 03'),(14, 'Materia MES 04'),(15, 'Materia MES 05')");
		stm.execute();
		System.out.println("STATUS---> SUCESSO... (Tabelas limpas)\n");
		} catch (SQLException e) {
			System.out.println("STATUS---> FALHA... (" + e.getMessage() + ")\n");
		} catch (Exception e) {
			System.out.println("STATUS---> FALHA... (" + e.getMessage() + ")\n");
		}
		ConexaoMYSQL.fecharConexao();
	}
}
