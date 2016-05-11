package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.negocio.pedagogico.SituacaoDoAlunoNaTurmaDao;
import br.com.cdan.negocio.pedagogico.contrato.MatriculaDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MatriculaFabricaTest;

public class SituacaoDoAlunoNaTurmaFabricaTest {
	private static SituacaoDoAlunoNaTurmaFabricaTest instance = null;

	public static synchronized SituacaoDoAlunoNaTurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDoAlunoNaTurmaFabricaTest();
		}
		return instance;
	}

	public SituacaoDoAlunoNaTurma criaSituacaoDoAlunoNaTurma() {
		SituacaoDoAlunoNaTurma s = new SituacaoDoAlunoNaTurma();
		s.setAbreviatura("abreviatura");
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		s.setMatriculas(matriculas);
		//
		return s;
	}

	public SituacaoDoAlunoNaTurma criaSituacaoDoAlunoNaTurmaPersistido(EntityManager em) {
		SituacaoDoAlunoNaTurma s = criaSituacaoDoAlunoNaTurma();
		SituacaoDoAlunoNaTurmaDao dao = new SituacaoDoAlunoNaTurmaDao(em);
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		MatriculaDao matriculaDao = new MatriculaDao(em);
		s.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		s.setMatriculas(matriculas);
		//
		dao.persist(s);
		return s;
	}
}
