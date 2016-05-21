package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeDisciplina;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.TipoDeDisciplinaDao;

public class TipoDeDisciplinaFabricaTest extends FabricaTest {
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
		t.setDescricao(criarStringDinamicaPorTamanho(100));
		//
		return t;
	}

	public TipoDeDisciplina criaTipoDeDisciplinaPersistido(EntityManager em) {
		TipoDeDisciplina t = criaTipoDeDisciplina();
		TipoDeDisciplinaDao dao = new TipoDeDisciplinaDao(em);
		//
		dao.persist(t);
		return t;
	}
}
