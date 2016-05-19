package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.OperadoraCartao;
import br.com.cdan.negocio.financeiro.OperadoraCartaoDao;

public class OperadoraCartaoFabricaTest {
	private static OperadoraCartaoFabricaTest instance = null;

	public static synchronized OperadoraCartaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new OperadoraCartaoFabricaTest();
		}
		return instance;
	}

	public OperadoraCartao criaOperadoraCartao() {
		OperadoraCartao o = new OperadoraCartao();
		o.setAtivo(Boolean.TRUE);
		o.setNome("nome" + Math.random() * 10000);
		return o;
	}

	public OperadoraCartao criaOperadoraCartaoPersistido(EntityManager em) {
		OperadoraCartao o = criaOperadoraCartao();
		OperadoraCartaoDao dao = new OperadoraCartaoDao(em);
		dao.persist(o);
		return o;
	}
}