package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.Bairro;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.BairroDao;

public class BairroFabricaTest extends FabricaTest {
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
		bairro.setDescricao(criarStringDinamicaPorTamanho(100));
		return bairro;
	}

	public Bairro criaBairroPersistido(EntityManager em) {
		Bairro bairro = criaBairro(em);
		BairroDao dao = new BairroDao(em);
		dao.persist(bairro);
		return bairro;
	}

}
