package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.ContasAReceber;
import br.com.cdan.model.financeiro.ContasAReceber_Bolsa;
import br.com.cdan.negocio.biblioteca.BolsaDao;
import br.com.cdan.negocio.biblioteca.ContasAReceberDao;
import br.com.cdan.negocio.biblioteca.ContasAReceber_BolsaDao;

public class ContasAReceber_BolsaFabricaTest {
	private static ContasAReceber_BolsaFabricaTest instance = null;

	public static synchronized ContasAReceber_BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContasAReceber_BolsaFabricaTest();
		}
		return instance;
	}

	public ContasAReceber_Bolsa criaContasAReceber_Bolsa() {
		ContasAReceber_Bolsa c = new ContasAReceber_Bolsa();
		c.setBolsa(BolsaFabricaTest.getInstance().criaBolsa());
		c.setContasAReceber(ContasAReceberFabricaTest.getInstance().criaContasAReceber());
		c.setLancarParcelasComDesconto(Boolean.TRUE);
		c.setMotivoDescontoManual("motivoDescontoManual");
		c.setPercentual(Long.valueOf("20"));
		return c;
	}

	public ContasAReceber_Bolsa criaContasAReceber_BolsaFabricaPersistido(EntityManager em) {
		ContasAReceber_Bolsa c = criaContasAReceber_Bolsa();
		ContasAReceber_BolsaDao contasAReceber_BolsaDao = new ContasAReceber_BolsaDao();
		contasAReceber_BolsaDao.setEntityManager(em);
		// Bolsa
		BolsaDao bolsaDao = new BolsaDao();
		bolsaDao.setEntityManager(em);
		Bolsa bolsa = c.getBolsa();
		bolsaDao.persist(bolsa);
		c.setBolsa(bolsa);
		// Contas a receber
		ContasAReceberDao contasAReceberDao = new ContasAReceberDao();
		contasAReceberDao.setEntityManager(em);
		ContasAReceber contasAReceber = c.getContasAReceber();
		contasAReceberDao.persist(contasAReceber);
		c.setContasAReceber(contasAReceber);
		//
		return c;
	}
}