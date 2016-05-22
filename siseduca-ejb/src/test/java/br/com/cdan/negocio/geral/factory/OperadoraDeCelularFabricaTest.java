package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.OperadoraDeCelular;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.OperadoraDeCelularDao;

public class OperadoraDeCelularFabricaTest extends FabricaTest {
	private static OperadoraDeCelularFabricaTest instance = null;

	public static synchronized OperadoraDeCelularFabricaTest getInstance() {
		if (instance == null) {
			instance = new OperadoraDeCelularFabricaTest();
		}
		return instance;
	}

	public OperadoraDeCelular criaOperadoraDeCelular() {
		OperadoraDeCelular o = new OperadoraDeCelular();
		o.setAtivo(Boolean.TRUE);
		o.setDescricao(criarStringDinamicaPorTamanho(100));
		return o;
	}

	public OperadoraDeCelular criaOperadoraDeCelularPersistido(EntityManager em) {
		OperadoraDeCelular o = criaOperadoraDeCelular();
		OperadoraDeCelularDao dao = new OperadoraDeCelularDao(em);
		//
		dao.persist(o);
		return o;
	}

}
