package br.dev.codelabs.conexoes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMSSQL {

	private static String status = "STATUS---> Nao conectou!\n";
	static final String HOSTNAME = "localhost";
	static final String DATABASE = "universidade";
	static final String URL = "jdbc:sqlserver://" + HOSTNAME + ":1433;databaseName=" + DATABASE + ";integratedSecurity=false;encrypt=false;trustServerCertificate=false";
	static final String USERNAME = "sa";
	static final String PASSWORD = "12qw!@QW";

	public static Connection getConexaoMSSQL(){
		Connection conexao = null;
		try {
			String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
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
			ConexaoMSSQL.getConexaoMSSQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public static java.sql.Connection reiniciarConexao() {
		fecharConexao();
		return ConexaoMSSQL.getConexaoMSSQL();
	}
	
}
