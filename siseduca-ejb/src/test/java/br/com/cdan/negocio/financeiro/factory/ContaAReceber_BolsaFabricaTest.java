package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;
import br.com.cdan.negocio.financeiro.BolsaDao;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.ContaAReceber_BolsaDao;

public class ContaAReceber_BolsaFabricaTest {
	private static ContaAReceber_BolsaFabricaTest instance = null;

	public static synchronized ContaAReceber_BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAReceber_BolsaFabricaTest();
		}
		return instance;
	}

	public ContaAReceber_Bolsa criaContasAReceber_Bolsa() {
		ContaAReceber_Bolsa c = new ContaAReceber_Bolsa();
		c.setBolsa(BolsaFabricaTest.getInstance().criaBolsa());
		c.setContaAReceber(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		c.setLancarParcelasComDesconto(Boolean.TRUE);
		c.setMotivoDescontoManual("motivoDescontoManual");
		c.setPercentual(Long.valueOf("20"));
		return c;
	}

	public ContaAReceber_Bolsa criaContasAReceber_BolsaFabricaPersistido(EntityManager em) {
		ContaAReceber_Bolsa c = criaContasAReceber_Bolsa();
		ContaAReceber_BolsaDao dao = new ContaAReceber_BolsaDao(em);
		// Bolsa
		BolsaDao bolsaDao = new BolsaDao(em);
		Bolsa bolsa = c.getBolsa();
		bolsaDao.persist(bolsa);
		c.setBolsa(bolsa);
		// Contas a receber
		ContaAReceberDao contasAReceberDao = new ContaAReceberDao(em);
		ContaAReceber contasAReceber = c.getContaAReceber();
		contasAReceberDao.persist(contasAReceber);
		c.setContaAReceber(contasAReceber);
		//
		dao.persist(c);
		return c;
	}
}