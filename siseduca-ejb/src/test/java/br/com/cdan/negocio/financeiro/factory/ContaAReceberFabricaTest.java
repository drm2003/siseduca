package br.com.cdan.negocio.financeiro.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.pedagogico.curso.factory.InvestimentoFabricaTest;

public class ContaAReceberFabricaTest {
	private static ContaAReceberFabricaTest instance = null;

	public static synchronized ContaAReceberFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAReceberFabricaTest();
		}
		return instance;
	}

	public ContaAReceber criaContaAReceber(EntityManager em) {
		ContaAReceber c = new ContaAReceber();
		c.setAtivo(Boolean.TRUE);
		c.setComplementoPlanoDeConta("complementoPlanoDeConta");
		c.setConta(ContaFabricaTest.getInstance().criaContaPersistido(em));
		//
		c.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		c.setDataVencimento(Calendar.getInstance());
		c.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		c.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimentoPersistido(em));
		c.setObservacao("observacao");
		c.setPlanoDeConta(PlanoDeContaFabricaTest.getInstance().criaPlanoDeContasPersistido(em));
		c.setPrimeiraParcela(InvestimentoFabricaTest.getInstance().criaInvestimentoPersistido(em));
		c.setTipoDeCobranca(
				TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimentoPersistido(em));
		c.setValorDiferenciadoPrimeiraParcela(Boolean.TRUE);
		return c;
	}

	public ContaAReceber criaContaAReceberPersistido(EntityManager em) {
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		ContaAReceber c = criaContaAReceber(em);
		contaAReceberDao.persist(c);
		return c;
	}
}
