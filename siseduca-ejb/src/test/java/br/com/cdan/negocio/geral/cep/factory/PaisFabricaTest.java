package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.Pais;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.PaisDao;

public class PaisFabricaTest extends FabricaTest {
	private static PaisFabricaTest instance = null;

	public static synchronized PaisFabricaTest getInstance() {
		if (instance == null) {
			instance = new PaisFabricaTest();
		}
		return instance;
	}

	public Pais criaPais() {
		Pais pais = new Pais();
		pais.setAtivo(Boolean.TRUE);
		pais.setDescricao(criarStringDinamicaPorTamanho(100));
		return pais;
	}

	public Pais criaPaisPersistido(EntityManager em) {
		Pais pais = criaPais();
		PaisDao dao = new PaisDao(em);
		dao.persist(pais);
		return pais;
	}

}
