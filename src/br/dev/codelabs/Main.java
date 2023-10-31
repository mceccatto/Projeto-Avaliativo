package br.dev.codelabs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import br.dev.codelabs.alunos.iAluno;
import br.dev.codelabs.avaliacoes.iAvaliacao;
import br.dev.codelabs.fabricas.iFabrica;
import br.dev.codelabs.matriculas.BachareladoDec;
import br.dev.codelabs.matriculas.BasicDec;
import br.dev.codelabs.matriculas.MestradoDec;
import br.dev.codelabs.matriculas.TecnicoDec;
import br.dev.codelabs.matriculas.iMatricula;
import br.dev.codelabs.persistencias.Limpar;
import br.dev.codelabs.persistencias.PersistenciaMYSQL;

public class Main {

	public static void main(String[] args) {
		Limpar.limpar();
		iFabrica persistencia = new PersistenciaMYSQL();
		Universidade instituicao = new Universidade(persistencia);
		
		iAluno aluno1 = instituicao.novoAluno("00000000000", "Frederico", getDateTime());
		System.out.println();
		//CURSO / MATERIA / ALUNO / NOTA / HORARIO
		iAvaliacao aluno1A1 = instituicao.novaAvaliacaoTecnico((long)1, (long)1, (long)1, 9.0, getDateTime());
		iAvaliacao aluno1A2 = instituicao.novaAvaliacaoTecnico((long)1, (long)2, (long)1, 7.0, getDateTime());
		iAvaliacao aluno1A3 = instituicao.novaAvaliacaoTecnico((long)1, (long)3, (long)1, 6.9, getDateTime());
		iAvaliacao aluno1A4 = instituicao.novaAvaliacaoTecnico((long)1, (long)4, (long)1, 8.5, getDateTime());
		iAvaliacao aluno1A5 = instituicao.novaAvaliacaoTecnico((long)1, (long)5, (long)1, 9.9, getDateTime());
		System.out.println();
		//CURSO / ALUNO
		iAvaliacao aluno1C1 = instituicao.checarAvaliacaoTecnico((long)1, (long)1);
		
		
		iAluno aluno2 = instituicao.novoAluno("11111111111", "Alice", getDateTime());
		System.out.println();
		//CURSO / MATERIA / ALUNO / NOTA / HORARIO
		iAvaliacao avaliacaoA21 = instituicao.novaAvaliacaoBacharelado((long)2, (long)6, (long)2, 9.5, getDateTime());
		iAvaliacao avaliacaoA22 = instituicao.novaAvaliacaoBacharelado((long)2, (long)7, (long)2, 8.3, getDateTime());
		iAvaliacao avaliacaoA23 = instituicao.novaAvaliacaoBacharelado((long)2, (long)8, (long)2, 7.2, getDateTime());
		iAvaliacao avaliacaoA24 = instituicao.novaAvaliacaoBacharelado((long)2, (long)9, (long)2, 5.8, getDateTime());
		iAvaliacao avaliacaoA25 = instituicao.novaAvaliacaoBacharelado((long)2, (long)10, (long)2, 7.6, getDateTime());
		System.out.println();
		//CURSO / ALUNO
		iAvaliacao checagemA21 = instituicao.checarAvaliacaoBacharelado((long)2, (long)2);
		
		
		iAluno aluno3 = instituicao.novoAluno("22222222222", "Pedro", getDateTime());
		System.out.println();
		//CURSO / MATERIA / ALUNO / CONCEITO / HORARIO
		iAvaliacao avaliacaoA31 = instituicao.novaAvaliacaoMestrado((long)3, (long)11, (long)3, "A", getDateTime());
		iAvaliacao avaliacaoA32 = instituicao.novaAvaliacaoMestrado((long)3, (long)12, (long)3, "B", getDateTime());
		iAvaliacao avaliacaoA33 = instituicao.novaAvaliacaoMestrado((long)3, (long)13, (long)3, "C", getDateTime());
		iAvaliacao avaliacaoA34 = instituicao.novaAvaliacaoMestrado((long)3, (long)14, (long)3, "D", getDateTime());
		iAvaliacao avaliacaoA35 = instituicao.novaAvaliacaoMestrado((long)3, (long)15, (long)3, "B", getDateTime());
		System.out.println();
		//CURSO / ALUNO
		iAvaliacao checagemA31 = instituicao.checarAvaliacaoMestrado((long)3, (long)3);
		
		
		//EXECUTANDO MATRICULA COM DECORATOR
		iMatricula matriculaA41 = new BasicDec();
		matriculaA41 = instituicao.novaMatricula(matriculaA41, "Tecnico");
		System.out.println(matriculaA41.matricular());
		matriculaA41 = instituicao.novaMatricula(matriculaA41, "Bacharelado");
		System.out.println(matriculaA41.matricular());
		matriculaA41 = instituicao.novaMatricula(matriculaA41, "Mestrado");
		System.out.println(matriculaA41.matricular());
	}

	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime());
	}

}