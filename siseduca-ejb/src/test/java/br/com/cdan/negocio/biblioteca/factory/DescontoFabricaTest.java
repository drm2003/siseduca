package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.Desconto;
import br.com.cdan.negocio.biblioteca.BolsaDao;
import br.com.cdan.negocio.biblioteca.DescontoDao;

public class DescontoFabricaTest {
	private static DescontoFabricaTest instance = null;

	public static synchronized DescontoFabricaTest getInstance() {
		if (instance == null) {
			instance = new DescontoFabricaTest();
		}
		return instance;
	}

	public Desconto criaDesconto() {
		Desconto d = new Desconto();
		d.setBolsa(BolsaFabricaTest.getInstance().criaBolsa());
		d.setDiasAntesDoVencimento(Long.valueOf("10"));
		d.setPercentualDesconto(BigDecimal.ONE);
		d.setAtivo(Boolean.TRUE);
		return d;
	}

	public Desconto criaDescontoPersistido(EntityManager em) {
		DescontoDao descontoDao = new DescontoDao();
		descontoDao.setEntityManager(em);
		Desconto d = criaDesconto();
		// Bolsa
		BolsaDao bolsaDao = new BolsaDao();
		bolsaDao.setEntityManager(em);
		Bolsa bolsa = d.getBolsa();
		d.setBolsa(bolsa);
		//
		descontoDao.persist(d);
		return d;
	}
}