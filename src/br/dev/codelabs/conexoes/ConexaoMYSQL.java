package br.dev.codelabs.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMYSQL {
	
	private static String status = "STATUS---> Nao conectou!\n";
	static final String HOSTNAME = "localhost";
	static final String DATABASE = "universidade";
	static final String URL = "jdbc:mysql://" + HOSTNAME + "/" + DATABASE;
	static final String USERNAME = "root";
	static final String PASSWORD = "root";

	public static Connection getConexaoMYSQL(){
		Connection conexao = null;
		try {
			String driver = "com.mysql.cj.jdbc.Driver";
			Class.forName(driver);
			conexao = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			if(conexao != null) {
				status = ("STATUS---> Conectado com sucesso!\n");
			} else {
				status = ("STATUS---> Nao foi possivel conectar ao banco de dados!\n");
			}
			return conexao;
		} catch(ClassNotFoundException e) {
			System.out.println("STATUS---> O driver expecificado nao foi encontrado!\n");
			System.out.println(e.getMessage());
			return null;
		} catch (SQLException e) {
			System.out.println("STATUS---> Nao foi possivel conectar ao banco de dados!\n");
			System.out.println(e.getMessage());
			return null;
		}
	}

	public static String statusConexao() {
		return status;
	}

	public static boolean fecharConexao() {
		try {
			ConexaoMYSQL.getConexaoMYSQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static java.sql.Connection reiniciarConexao() {
		fecharConexao();
		return ConexaoMYSQL.getConexaoMYSQL();
	}
}
