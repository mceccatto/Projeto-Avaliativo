package br.dev.codelabs.alunos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Aluno implements iAluno {
	
	private String Cpf;
	private String Nome;
	private String DataRegistro;
	private Connection Conexao = null;

	public Aluno(String Cpf, String Nome, String DataRegistro, Connection Conexao) {
		this.Cpf = Cpf;
		this.Nome = Nome;
		this.DataRegistro = DataRegistro;
		this.Conexao = Conexao;
	}

	@Override
	public String registrar() {
		if(this.Conexao != null) {
			PreparedStatement stm = null;
			try {
				stm = Conexao.prepareStatement("INSERT INTO alunos (cpf, nome, dataregistro) VALUES (?,?,?)");
				stm.setString(1, this.Cpf);
				stm.setString(2, this.Nome);
				stm.setString(3, this.DataRegistro);
				stm.execute();
				return "STATUS---> SUCESSO... (" + this.Cpf + " / " + this.Nome + " / " + this.DataRegistro + ")";
			} catch (SQLException e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			} catch (Exception e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			}
		}
		return null;
	}

}
