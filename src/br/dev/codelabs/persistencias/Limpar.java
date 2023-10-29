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
		//SELECT Concat('TRUNCATE TABLE ', TABLE_NAME) FROM INFORMATION_SCHEMA.TABLES WHERE table_schema = 'universidade'
		stm = conexao.prepareStatement("TRUNCATE TABLE alunos");
		stm.execute();
		stm = conexao.prepareStatement("TRUNCATE TABLE avaliacoes");
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
