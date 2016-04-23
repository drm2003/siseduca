package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;
import br.com.cdan.negocio.biblioteca.BolsaDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.ContasAReceber_BolsaDao;

public class ContasAReceber_BolsaFabricaTest {
	private static ContasAReceber_BolsaFabricaTest instance = null;

	public static synchronized ContasAReceber_BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAReceber_BolsaFabricaTest();
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
		ContasAReceber_BolsaDao contasAReceber_BolsaDao = new ContasAReceber_BolsaDao();
		contasAReceber_BolsaDao.setEntityManager(em);
		// Bolsa
		BolsaDao bolsaDao = new BolsaDao();
		bolsaDao.setEntityManager(em);
		Bolsa bolsa = c.getBolsa();
		bolsaDao.persist(bolsa);
		c.setBolsa(bolsa);
		// Contas a receber
		ContaAReceberDao contasAReceberDao = new ContaAReceberDao();
		contasAReceberDao.setEntityManager(em);
		ContaAReceber contasAReceber = c.getContaAReceber();
		contasAReceberDao.persist(contasAReceber);
		c.setContaAReceber(contasAReceber);
		//
		return c;
	}
}