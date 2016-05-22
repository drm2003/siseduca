package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.EstadoCivil;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.EstadoCivilDao;

public class EstadoCivilFabricaTest extends FabricaTest {
	private static EstadoCivilFabricaTest instance = null;

	public static synchronized EstadoCivilFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstadoCivilFabricaTest();
		}
		return instance;
	}

	public EstadoCivil criaEstadoCivil() {
		EstadoCivil e = new EstadoCivil();
		//
		e.setAtivo(Boolean.TRUE);
		e.setDescricao(criarStringDinamicaPorTamanho(30));
		//
		return e;
	}

	public EstadoCivil criaEstadoCivilPersistido(EntityManager em) {
		EstadoCivil e = criaEstadoCivil();
		EstadoCivilDao dao = new EstadoCivilDao(em);
		//

		dao.persist(e);
		return e;
	}

}
