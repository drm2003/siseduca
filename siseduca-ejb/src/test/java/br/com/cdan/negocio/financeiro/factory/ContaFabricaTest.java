package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Conta;
import br.com.cdan.negocio.financeiro.ContaDao;

public class ContaFabricaTest {
	private static ContaFabricaTest instance = null;

	public static synchronized ContaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaFabricaTest();
		}
		return instance;
	}

	public Conta criaConta() {
		Conta c = new Conta();
		c.setAtivo(Boolean.TRUE);
		c.setBanco(BancoFabricaTest.getInstance().criaBanco());
		//
		c.setEncerrada(Boolean.FALSE);
		c.setNumero("numero");
		c.setTitular("titular");
		//
		return c;
	}

	public Conta criaContaPersistido(EntityManager em) {
		ContaDao dao = new ContaDao(em);
		Conta c = criaConta();
		//
		dao.persist(c);
		return c;
	}
}
