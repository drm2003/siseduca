package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.TipoDeInvestimento;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.negocio.biblioteca.TipoDeInvestimentoDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;

public class TipoDeInvestimentoFabricaTest {
	private static TipoDeInvestimentoFabricaTest instance = null;

	public static synchronized TipoDeInvestimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeInvestimentoFabricaTest();
		}
		return instance;
	}

	public TipoDeInvestimento criaTipoDeInvestimento() {
		TipoDeInvestimento t = new TipoDeInvestimento();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		Set<Turma_Disciplina> turmas_disciplinas = new LinkedHashSet<>();
		turmas_disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turmas_disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		t.setTurmas_Disciplinas(turmas_disciplinas);
		return t;
	}

	public TipoDeInvestimento criaTipoDeInvestimentoPersistido(EntityManager em) {
		TipoDeInvestimento t = criaTipoDeInvestimento();
		TipoDeInvestimentoDao dao = new TipoDeInvestimentoDao(em);
		//
		Set<Turma_Disciplina> turmas_disciplinas = new LinkedHashSet<>();
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		t.getTurmas_Disciplinas().forEach(turma_disciplina -> {
			turma_DisciplinaDao.persist(turma_disciplina);
			turmas_disciplinas.add(turma_disciplina);
		});
		t.setTurmas_Disciplinas(turmas_disciplinas);
		//
		dao.persist(t);
		return t;
	}
}
