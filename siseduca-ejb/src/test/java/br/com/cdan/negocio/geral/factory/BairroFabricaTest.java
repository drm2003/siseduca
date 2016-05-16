package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Bairro;
import br.com.cdan.negocio.geral.BairroDao;

public class BairroFabricaTest {
	private static BairroFabricaTest instance = null;

	public static BairroFabricaTest getInstance() {
		if (instance == null) {
			instance = new BairroFabricaTest();
		}
		return instance;
	}

	public Bairro criaBairro(EntityManager em) {
		Bairro bairro = new Bairro();
		bairro.setAtivo(Boolean.TRUE);
		bairro.setCidade(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		bairro.setDescricao("teste");
		return bairro;
	}

	public Bairro criaBairroPersistido(EntityManager em) {
		Bairro bairro = criaBairro(em);
		BairroDao dao = new BairroDao(em);
		dao.persist(bairro);
		return bairro;
	}

}
