package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAPagar;
import br.com.cdan.negocio.financeiro.ContaAPagarDao;
import br.com.cdan.negocio.pedagogico.curso.factory.InvestimentoFabricaTest;

public class ContaAPagarFabricaTest {
	private static ContaAPagarFabricaTest instance = null;

	public static synchronized ContaAPagarFabricaTest getInstance() {
		if (instance == null) {
			instance = new ContaAPagarFabricaTest();
		}
		return instance;
	}

	public ContaAPagar criaContasAPagar(EntityManager em) {
		ContaAPagar c = new ContaAPagar();

		c.setAtivo(Boolean.TRUE);
		c.setComplementoPlanoDeConta("complemento plano de conta");
		//
		c.setConta(ContaFabricaTest.getInstance().criaContaPersistido(em));
		c.setDataCompetenciaPlanoDeConta(Calendar.getInstance());
		c.setDataVencimento(Calendar.getInstance());
		c.setDocumentoPlanoDeConta("documentoPlanoDeConta");
		c.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimentoPersistido(em));
		c.setMora(BigDecimal.ZERO);
		c.setObservacao("observacao");
		c.setPlanoDeConta(PlanoDeContaFabricaTest.getInstance().criaPlanoDeContasPersistido(em));
		c.setPrimeiraParcela(InvestimentoFabricaTest.getInstance().criaInvestimentoPersistido(em));
		c.setTipoDeCobranca(
				TipoDeCobrancaRecebimentoFabricaTest.getInstance().criaTipoDeCobrancaRecebimentoPersistido(em));
		c.setValorDiferenciadoPrimeiraParcela(Boolean.TRUE);
		return c;
	}

	public ContaAPagar criaContasAPagarPersistido(EntityManager em) {
		ContaAPagar c = criaContasAPagar(em);
		ContaAPagarDao dao = new ContaAPagarDao(em);
		dao.setEntityManager(em);
		//
		dao.persist(c);
		return c;
	}

}
