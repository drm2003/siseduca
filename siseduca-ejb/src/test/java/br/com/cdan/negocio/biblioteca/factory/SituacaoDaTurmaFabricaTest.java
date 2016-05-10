package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SituacaoDaTurma;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.negocio.biblioteca.SituacaoDaTurmaDao;
import br.com.cdan.negocio.biblioteca.TurmaDao;

public class SituacaoDaTurmaFabricaTest {
	private static SituacaoDaTurmaFabricaTest instance = null;

	public static synchronized SituacaoDaTurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDaTurmaFabricaTest();
		}
		return instance;
	}

	public SituacaoDaTurma criaSituacaoDaTurma() {
		SituacaoDaTurma s = new SituacaoDaTurma();
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		s.setTurmas(turmas);
		//
		return s;
	}

	public SituacaoDaTurma criaSituacaoDaTurmaPersistido(EntityManager em) {
		SituacaoDaTurma s = criaSituacaoDaTurma();
		SituacaoDaTurmaDao dao = new SituacaoDaTurmaDao(em);
		//
		Set<Turma> turmas = new LinkedHashSet<>();
		TurmaDao turmaDao = new TurmaDao(em);
		s.getTurmas().forEach(turma -> {
			turmaDao.persist(turma);
			turmas.add(turma);
		});
		s.setTurmas(turmas);
		//
		dao.persist(s);
		return s;
	}
}
