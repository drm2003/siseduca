package br.com.cdan.negocio.pedagogico.curso.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeSituacaoInvestimento;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.negocio.estoque.factory.ItemFabricaTest;
import br.com.cdan.negocio.financeiro.factory.ContaAReceberFabricaTest;
import br.com.cdan.negocio.geral.factory.CategoriaFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.InvestimentoDao;

public class InvestimentoFabricaTest {
	private static InvestimentoFabricaTest instance = null;

	public static synchronized InvestimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new InvestimentoFabricaTest();
		}
		return instance;
	}

	public Investimento criaInvestimento(EntityManager em) {
		Investimento i = new Investimento();
		i.setAtivo(Boolean.TRUE);
		i.setCategoria(CategoriaFabricaTest.getInstance().criaCategoriaPersistido(em));
		i.setConsiderarMesAtual(Boolean.TRUE);
		// Contas a receber
		i.setContaAReceber(ContaAReceberFabricaTest.getInstance().criaContaAReceberPersistido(em));
		// Contas a receber primeira parcela
		i.setContaAReceberPrimeiraParcela(ContaAReceberFabricaTest.getInstance().criaContaAReceberPersistido(em));
		i.setDataDiferenciadaPrimeiraParcela(Calendar.getInstance());
		i.setDataInicial(Calendar.getInstance());
		i.setDescricaoPlano("descricaoPlano");
		i.setItem(ItemFabricaTest.getInstance().criaItemPersistido(em));
		//
		i.setNumeroDeParcelas(Long.valueOf("1"));
		i.setTipoDeInvestimento(TipoDeInvestimentoFabricaTest.getInstance().criaTipoDeInvestimentoPersistido(em));
		i.setTipoDeSituacao(EnumTipoDeSituacaoInvestimento.QUITADA);
		i.setValorDaParcela(BigDecimal.TEN);
		i.setValorDaPrimeiraParcelaDiferenciada(Boolean.TRUE);
		i.setValorPrimeiraParcelaDiferenciado(BigDecimal.TEN);
		//
		return i;
	}

	public Investimento criaInvestimentoPersistido(EntityManager em) {
		Investimento i = criaInvestimento(em);
		InvestimentoDao dao = new InvestimentoDao(em);
		//
		dao.persist(i);
		return i;
	}
}