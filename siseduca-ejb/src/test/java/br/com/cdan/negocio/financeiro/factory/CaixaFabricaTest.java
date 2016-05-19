package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.negocio.financeiro.CaixaDao;
import br.com.cdan.negocio.geral.factory.CategoriaFabricaTest;

public class CaixaFabricaTest {
	private static CaixaFabricaTest instance = null;

	public static synchronized CaixaFabricaTest getInstance() {
		if (instance == null) {
			instance = new CaixaFabricaTest();
		}
		return instance;
	}

	public Caixa criaCaixa(EntityManager em) {
		Caixa c = new Caixa();
		c.setCategoria(CategoriaFabricaTest.getInstance().criaCategoriaPersistido(em));
		c.setComplementoPlanoDeContas("complementoPlanoDeContas");
		c.setConta(ContaFabricaTest.getInstance().criaContaPersistido(em));
		c.setDataDeLancamento(Calendar.getInstance());
		c.setDocumento("documento " + Math.random() * 10000);
		c.setObservacoes("observacoes");
		c.setTipo(EnumTipoDePlanoDeContas.ENTRADA);
		c.setValor(BigDecimal.TEN);
		c.setTipoDeMovimentacao(
				TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimentoPersistido(em));
		c.setAtivo(Boolean.TRUE);
		return c;
	}

	public Caixa criaCaixaPersistido(EntityManager em) {
		CaixaDao caixaDao = new CaixaDao(em);
		Caixa c = criaCaixa(em);
		//
		caixaDao.persist(c);
		return c;
	}
}