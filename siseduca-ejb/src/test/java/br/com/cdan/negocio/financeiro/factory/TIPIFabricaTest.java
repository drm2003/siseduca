package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.TIPI;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.financeiro.TIPIDao;

public class TIPIFabricaTest extends FabricaTest {
	private static TIPIFabricaTest instance = null;

	public static synchronized TIPIFabricaTest getInstance() {
		if (instance == null) {
			instance = new TIPIFabricaTest();
		}
		return instance;
	}

	public TIPI criaTIPI(EntityManager em) {
		TIPI t = new TIPI();
		t.setAtivo(Boolean.TRUE);
		t.setCodigoReceita(Long.parseLong(criarStringDinamicaPorTamanho(10)));
		t.setDescricao("descricao" + Math.random() * 10000);
		return t;
	}

	public TIPI criaTIPIPersistido(EntityManager em) {
		TIPI t = criaTIPI(em);
		TIPIDao dao = new TIPIDao(em);
		//
		dao.persist(t);
		return t;
	}
}
