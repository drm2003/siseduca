package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Banco;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.financeiro.BancoDao;

public class BancoFabricaTest extends FabricaTest {
	private static BancoFabricaTest instance = null;

	public static synchronized BancoFabricaTest getInstance() {
		if (instance == null) {
			instance = new BancoFabricaTest();
		}
		return instance;
	}

	public Banco criaBanco() {
		Banco b = new Banco();
		b.setAgencia("agencia");
		b.setAtivo(Boolean.TRUE);
		b.setNome(criarStringDinamicaPorTamanho(50));
		return b;
	}

	public Banco criaBancoPersistido(EntityManager em) {
		Banco b = criaBanco();
		BancoDao bancoDao = new BancoDao(em);
		bancoDao.persist(b);
		return b;
	}
}
