package br.com.cdan.negocio.financeiro.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.Caixa;
import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;
import br.com.cdan.negocio.financeiro.CaixaDao;
import br.com.cdan.negocio.financeiro.ContaAReceberDao;
import br.com.cdan.negocio.financeiro.TipoDeCobrancaRecebimentoDao;

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
		t.setDescricao("descricao");
		//
		Set<Caixa> caixas = new LinkedHashSet<>();
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		caixas.add(CaixaFabricaTest.getInstance().criaCaixa());
		t.setCaixas(caixas);
		//
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		t.setContasAReceber(contasAReceber);
		return t;
	}

	public TipoDeCobrancaRecebimento criaTipoDeCobrancaRecebimentoPersistido(EntityManager em) {
		TipoDeCobrancaRecebimento t = criaTipoDeCobrancaRecebimento();
		TipoDeCobrancaRecebimentoDao dao = new TipoDeCobrancaRecebimentoDao(em);
		//
		CaixaDao caixaDao = new CaixaDao(em);
		Set<Caixa> caixas = new LinkedHashSet<>();
		t.getCaixas().forEach(caixa -> {
			caixaDao.persist(caixa);
			caixas.add(caixa);
		});
		t.setCaixas(caixas);
		//
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
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
