package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumEspecieDesconto;
import br.com.cdan.comum.EnumTipoDeDesconto;
import br.com.cdan.model.financeiro.Bolsa;
import br.com.cdan.negocio.financeiro.BolsaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.DadoBancarioFabricaTest;

public class BolsaFabricaTest {
	private static BolsaFabricaTest instance = null;

	public static synchronized BolsaFabricaTest getInstance() {
		if (instance == null) {
			instance = new BolsaFabricaTest();
		}
		return instance;
	}

	public Bolsa criaBolsa(EntityManager em) {
		Bolsa b = new Bolsa();
		b.setAtivo(Boolean.TRUE);
		b.setDadoBancario(DadoBancarioFabricaTest.getInstance().criaDadoBancarioPersistido(em));
		b.setDescricao("descricao");
		b.setEspecieDesconto(EnumEspecieDesconto.PERCENTUAL);
		b.setPadraoFranquia("padraoFranquia");
		b.setTipoDeDesconto(EnumTipoDeDesconto.BOLSA);
		return b;
	}

	public Bolsa criaBolsaPersistido(EntityManager em) {
		Bolsa b = criaBolsa(em);
		BolsaDao dao = new BolsaDao(em);
		dao.persist(b);
		return b;
	}
}