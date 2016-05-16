package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;
import br.com.cdan.negocio.financeiro.ContaAReceber_BolsaDao;

public class ContaAReceber_BolsaFabricaTest {
	private static ContaAReceber_BolsaFabricaTest instance = null;

	public static synchronized ContaAReceber_BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAReceber_BolsaFabricaTest();
		}
		return instance;
	}

	public ContaAReceber_Bolsa criaContasAReceber_Bolsa(EntityManager em) {
		ContaAReceber_Bolsa c = new ContaAReceber_Bolsa();
		c.setBolsa(BolsaFabricaTest.getInstance().criaBolsaPersistido(em));
		c.setContaAReceber(ContaAReceberFabricaTest.getInstance().criaContaAReceberPersistido(em));
		c.setLancarParcelasComDesconto(Boolean.TRUE);
		c.setMotivoDescontoManual("motivoDescontoManual");
		c.setPercentual(Long.valueOf("20"));
		return c;
	}

	public ContaAReceber_Bolsa criaContasAReceber_BolsaFabricaPersistido(EntityManager em) {
		ContaAReceber_Bolsa c = criaContasAReceber_Bolsa(em);
		ContaAReceber_BolsaDao dao = new ContaAReceber_BolsaDao(em);
		dao.persist(c);
		return c;
	}
}