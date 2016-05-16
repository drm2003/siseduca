package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.InvestimentoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.SituacaoDoAlunoNaTurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.geral.factory.SituacaoDoContratoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

public class MatriculaFabricaTest {
	private static MatriculaFabricaTest instance = null;

	public static synchronized MatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MatriculaFabricaTest();
		}
		return instance;
	}

	public Matricula criaMatricula(EntityManager em) {
		Matricula m = new Matricula();
		m.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		m.setAtivo(Boolean.TRUE);
		m.setDataInicio(Calendar.getInstance());
		m.setDataTermino(Calendar.getInstance());
		//
		m.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimentoPersistido(em));
		m.setMatrizCurricular(Long.valueOf("10"));
		m.setNumeroContrato("numeroContrato");
		m.setSituacaoDoAlunoNaTurma(
				SituacaoDoAlunoNaTurmaFabricaTest.getInstance().criaSituacaoDoAlunoNaTurmaPersistido(em));
		m.setSituacaoDoContrato(SituacaoDoContratoFabricaTest.getInstance().criaSituacaoDoContratoPersistido(em));
		m.setTipoDeContrato(TipoDeContratoFabricaTest.getInstance().criaTipoDeContratoPersistido(em));
		m.setTurma(Long.valueOf("10"));
		return m;
	}

	public Matricula criaMatriculaPersistido(EntityManager em) {
		Matricula m = criaMatricula(em);
		MatriculaDao dao = new MatriculaDao(em);
		dao.persist(m);
		return m;
	}

}
