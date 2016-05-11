package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.model.geral.Categoria;
import br.com.cdan.negocio.financeiro.CaixaDao;
import br.com.cdan.negocio.financeiro.TipoDeCobrancaRecebimentoDao;
import br.com.cdan.negocio.geral.CategoriaDao;
import br.com.cdan.negocio.geral.factory.CategoriaFabricaTest;

public class CaixaFabricaTest {
	private static CaixaFabricaTest instance = null;

	public static synchronized CaixaFabricaTest getInstance() {
		if (instance == null) {
			instance = new CaixaFabricaTest();
		}
		return instance;
	}

	public Caixa criaCaixa() {
		Caixa c = new Caixa();
		c.setCategoria(CategoriaFabricaTest.getInstance().criaCategoria());
		c.setComplementoPlanoDeContas("complementoPlanoDeContas");
		c.setConta(ContaFabricaTest.getInstance().criaConta());
		c.setDataDeLancamento(Calendar.getInstance());
		c.setDocumento("documento");
		c.setObservacoes("observacoes");
		c.setTipo(EnumTipoDePlanoDeContas.ENTRADA);
		c.setValor(BigDecimal.TEN);
		return c;
	}

	public Caixa criaCaixaPersistido(EntityManager em) {
		CaixaDao caixaDao = new CaixaDao(em);
		Caixa c = criaCaixa();
		//
		CategoriaDao categoriaDao = new CategoriaDao(em);
		Categoria categoria = c.getCategoria();
		categoriaDao.persist(categoria);
		c.setCategoria(categoria);
		//
		TipoDeCobrancaRecebimentoDao tipoDeCobrancaRecebimentoDao = new TipoDeCobrancaRecebimentoDao(em);
		TipoDeCobrancaRecebimento tipoDeMovimentacao = c.getTipoDeMovimentacao();
		tipoDeCobrancaRecebimentoDao.persist(tipoDeMovimentacao);
		c.setTipoDeMovimentacao(tipoDeMovimentacao);
		//
		caixaDao.persist(c);
		return c;
	}
}