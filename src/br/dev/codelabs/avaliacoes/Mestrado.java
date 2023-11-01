package br.dev.codelabs.avaliacoes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mestrado extends Avaliacao {
	
	private final String Media = "D";
	private String Conceito;
	
	public Mestrado(Long Curso, Long Materia, Long Aluno, String Conceito, String DataRegistro, Connection Conexao) {
		super(Curso, Materia, Aluno, DataRegistro, Conexao);
		this.Conceito = Conceito;
	}
	
	public Mestrado(Long Curso, Long Aluno, Connection Conexao) {
		super(Curso, Aluno, Conexao);
	}
	
	public Mestrado(String Tipo, Long Curso, Long Materia, Long Aluno, String Conceito) {
		super(Tipo, Curso, Materia, Aluno);
		this.Conceito = Conceito;
	}

	@Override
	public String registrar() {
		if(this.Conexao != null) {
			PreparedStatement stm = null;
			try {
				stm = Conexao.prepareStatement("INSERT INTO avaliacoes (Tipo, Curso, Materia, Aluno, Conceito, DataRegistro) VALUES (?,?,?,?,?,?)");
				stm.setString(1, this.Tipo);
				stm.setLong(2, this.Curso);
				stm.setLong(3, this.Materia);
				stm.setLong(4, this.Aluno);
				stm.setString(5, this.Conceito);
				stm.setString(6, this.DataRegistro);
				stm.execute();
				return "STATUS---> SUCESSO... (" + this.Tipo + " / " + this.Curso + " / " + this.Materia + " / " + this.Aluno + " / " + this.Conceito + " / " + this.DataRegistro + ")";
			} catch (SQLException e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			} catch (Exception e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			}
		}
		return null;
	}

	@Override
	public String validar() {
		if(this.Conexao != null) {
			String statusGeral = "APROVADO(A)";
			String resultado = "";
			PreparedStatement stm = null;
			try {
				stm = Conexao.prepareStatement("SELECT A.Tipo AS Tipo, A.Curso AS CursoId, C.Nome AS CursoNome, A.Materia AS MateriaId, D.Nome AS MateriaNome, A.Aluno AS AlunoId, B.Nome AS AlunoNime, A.Conceito AS Conceito, A.Status AS STATUS FROM avaliacoes A LEFT JOIN alunos B ON B.Id = A.Aluno LEFT JOIN cursos C ON C.Id = A.Curso LEFT JOIN materias D ON D.Id = A.Materia WHERE A.Curso = ? AND A.Aluno = ?");
				stm.setLong(1, this.Curso);
				stm.setLong(2, this.Aluno);
				ResultSet busca = stm.executeQuery();
				String aluno = "";
				String tipo = "";
				Long cursoId = null;
				Long alunoId = null;
				while(busca.next()) {
					aluno = busca.getString(7);
					tipo = busca.getString(1);
					cursoId = busca.getLong(2);
					alunoId = busca.getLong(6);
					if(!busca.getString(8).equalsIgnoreCase(this.Media)) {
						resultado += "TIPO: " + busca.getString(1) + " | CURSO: " + busca.getString(3) + " | MATERIA: " + busca.getString(5) + " | ALUNO: " + busca.getString(7) + " | CONCEITO: " + busca.getString(8) + " | STATUS: Aprovado(a)\n";
						stm = Conexao.prepareStatement("UPDATE avaliacoes SET Status = 'Aprovado(a)' WHERE Curso = ? AND Materia = ? AND Aluno = ?");
						stm.setLong(1, busca.getLong(2));
						stm.setLong(2, busca.getLong(4));
						stm.setLong(3, busca.getLong(6));
					} else {
						statusGeral = "DESCREDENCIADO(A)";
						resultado += "TIPO: " + busca.getString(1) + " | CURSO: " + busca.getString(3) + " | MATERIA: " + busca.getString(5) + " | ALUNO: " + busca.getString(7) + " | CONCEITO: " + busca.getString(8) + " | STATUS: Reprovado(a)\n";
						stm = Conexao.prepareStatement("UPDATE avaliacoes SET Status = 'Reprovado(a)' WHERE Curso = ? AND Materia = ? AND Aluno = ?");
						stm.setLong(1, busca.getLong(2));
						stm.setLong(2, busca.getLong(4));
						stm.setLong(3, busca.getLong(6));
					}
					stm.execute();
				}
				return "STATUS---> SUCESSO... (Aluno(a) " + aluno + " está " + statusGeral + ")\n" + resultado;
			} catch (SQLException e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			} catch (Exception e) {
				return "STATUS---> FALHA... (" + e.getMessage() + ")";
			}
		}
		return "STATUS---> FALHA... (A conexão com o banco de dados não foi encontrada)";
	}

}
