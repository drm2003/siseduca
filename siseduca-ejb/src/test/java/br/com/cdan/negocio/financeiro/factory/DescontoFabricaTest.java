package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Desconto;
import br.com.cdan.negocio.financeiro.DescontoDao;

public class DescontoFabricaTest {
	private static DescontoFabricaTest instance = null;

	public static synchronized DescontoFabricaTest getInstance() {
		if (instance == null) {
			instance = new DescontoFabricaTest();
		}
		return instance;
	}

	public Desconto criaDesconto(EntityManager em) {
		Desconto d = new Desconto();
		d.setBolsa(BolsaFabricaTest.getInstance().criaBolsaPersistido(em));
		d.setDiasAntesDoVencimento(Long.valueOf("10"));
		d.setPercentualDesconto(BigDecimal.ONE);
		d.setAtivo(Boolean.TRUE);
		return d;
	}

	public Desconto criaDescontoPersistido(EntityManager em) {
		DescontoDao descontoDao = new DescontoDao(em);
		Desconto d = criaDesconto(em);
		//
		descontoDao.persist(d);
		return d;
	}
}