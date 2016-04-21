package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.contrato.Matricula;

public class MatriculaFabricaTest {
	private static MatriculaFabricaTest instance = null;

	public static synchronized MatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MatriculaFabricaTest();
		}
		return instance;
	}

	public Matricula criaMatricula() {
		Matricula m = new Matricula();
		m.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		m.setAtivo(Boolean.TRUE);
		m.setDataInicio(Calendar.getInstance());
		m.setDataTermino(Calendar.getInstance());
		// Disciplinas
		Set<DisciplinaMatricula> disciplinaMatriculas = new LinkedHashSet<>();
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		m.setDisciplinas(disciplinaMatriculas);
		//
		m.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimento());
		m.setMatrizCurricular(Long.valueOf("10"));
		m.setNumeroContrato("numeroContrato");
		m.setSituacaoDoAlunoNaTurma(SituacaoDoAlunoNaTurmaFabricaTest.getInstance().criaSituacaoDoAlunoNaTurma());
		m.setSituacaoDoContrato(SituacaoDoContratoFabricaTest.getInstance().criaSituacaoDoContrato());
		m.setTipoDeContrato(TipoDeContratoFabricaTest.getInstance().criaTipoDeContrato());
		m.setTurma(Long.valueOf("10"));
		return m;
	}

}
