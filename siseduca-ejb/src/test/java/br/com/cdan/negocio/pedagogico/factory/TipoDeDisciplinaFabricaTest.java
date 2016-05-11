package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeDisciplina;
import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.negocio.pedagogico.TipoDeDisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.DisciplinaFabricaTest;

public class TipoDeDisciplinaFabricaTest {
	private static TipoDeDisciplinaFabricaTest instance = null;

	public static synchronized TipoDeDisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeDisciplinaFabricaTest();
		}
		return instance;
	}

	public TipoDeDisciplina criaTipoDeDisciplina() {
		TipoDeDisciplina t = new TipoDeDisciplina();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		// Disciplinas
		Set<Disciplina> disciplinas = new LinkedHashSet<>();
		disciplinas.add(DisciplinaFabricaTest.getInstance().criaDisciplina());
		t.setDisciplinas(disciplinas);
		//
		return t;
	}

	public TipoDeDisciplina criaTipoDeDisciplinaPersistido(EntityManager em) {
		TipoDeDisciplina t = criaTipoDeDisciplina();
		TipoDeDisciplinaDao dao = new TipoDeDisciplinaDao(em);
		//
		DisciplinaDao disciplinaDao = new DisciplinaDao(em);
		Set<Disciplina> disciplinas = new LinkedHashSet<>();
		t.getDisciplinas().forEach(disciplina -> {
			disciplinaDao.persist(disciplina);
			disciplinas.add(disciplina);
		});
		t.setDisciplinas(disciplinas);
		//
		dao.persist(t);
		return t;
	}
}
