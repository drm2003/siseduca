package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.negocio.biblioteca.CaixaDao;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.TipoDeCobrancaRecebimentoDao;

public class TipoDeCobrancaRecebimentoFabricaTest {
	private static TipoDeCobrancaRecebimentoFabricaTest instance = null;

	public static synchronized TipoDeCobrancaRecebimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCobrancaRecebimentoFabricaTest();
		}
		return instance;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimento() {
		TipoDeCobrancaRecebimento t = new TipoDeCobrancaRecebimento();
		t.setAtivo(Boolean.TRUE);
		// Caixas
		Set<Caixa> caixas = new LinkedHashSet<>();
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		t.setCaixas(caixas);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		t.setContasAReceber(contasAReceber);
		//
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimentoPersistido(EntityManager em) {
		TipoDeCobrancaRecebimento t = criaTipoDeCobrancaRecebimento();
		TipoDeCobrancaRecebimentoDao dao = new TipoDeCobrancaRecebimentoDao(em);
		// Caixas
		Set<Caixa> caixas = new LinkedHashSet<>();
		CaixaDao caixaDao = new CaixaDao(em);
		t.getCaixas().forEach(caixa -> {
			caixaDao.persist(caixa);
			caixas.add(caixa);
		});
		t.setCaixas(caixas);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		t.getContasAReceber().forEach(contaAReceber -> {
			contaAReceberDao.persist(contaAReceber);
			contasAReceber.add(contaAReceber);
		});
		t.setContasAReceber(contasAReceber);
		//
		dao.persist(t);
		return t;
	}

}
