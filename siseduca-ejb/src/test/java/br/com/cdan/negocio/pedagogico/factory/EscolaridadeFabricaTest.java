package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Escolaridade;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.EscolaridadeDao;

public class EscolaridadeFabricaTest extends FabricaTest {
	private static EscolaridadeFabricaTest instance = null;

	public static synchronized EscolaridadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new EscolaridadeFabricaTest();
		}
		return instance;
	}

	public Escolaridade criaEscolaridade() {
		Escolaridade e = new Escolaridade();
		e.setDescricao(criarStringDinamicaPorTamanho(100));
		e.setAtivo(Boolean.TRUE);
		return e;
	}

	public Escolaridade criaEscolaridadePersistido(EntityManager em) {
		Escolaridade e = criaEscolaridade();
		EscolaridadeDao dao = new EscolaridadeDao(em);
		dao.persist(e);
		return e;
	}
}