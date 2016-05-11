package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.model.financeiro.Desconto;
import br.com.cdan.negocio.financeiro.BolsaDao;
import br.com.cdan.negocio.financeiro.DescontoDao;

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
		DescontoDao descontoDao = new DescontoDao(em);
		Desconto d = criaDesconto();
		// Bolsa
		BolsaDao bolsaDao = new BolsaDao(em);
		Bolsa bolsa = d.getBolsa();
		bolsaDao.persist(bolsa);
		d.setBolsa(bolsa);
		//
		descontoDao.persist(d);
		return d;
	}
}