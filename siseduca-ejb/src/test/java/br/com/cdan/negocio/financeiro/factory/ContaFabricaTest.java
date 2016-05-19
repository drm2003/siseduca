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

	public Conta criaConta(EntityManager em) {
		Conta c = new Conta();
		c.setAtivo(Boolean.TRUE);
		c.setBanco(BancoFabricaTest.getInstance().criaBancoPersistido(em));
		//
		c.setEncerrada(Boolean.FALSE);
		c.setNumero("numero");
		c.setTitular("titular" + Math.random() * 10000);
		//
		return c;
	}

	public Conta criaContaPersistido(EntityManager em) {
		ContaDao dao = new ContaDao(em);
		Conta c = criaConta(em);
		//
		dao.persist(c);
		return c;
	}
}
