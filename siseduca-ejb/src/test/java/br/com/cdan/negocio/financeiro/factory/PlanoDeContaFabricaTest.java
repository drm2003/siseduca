package br.com.cdan.negocio.financeiro.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDePlanoDeContas;
import br.com.cdan.model.financeiro.PlanoDeConta;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.financeiro.PlanoDeContaDao;

public class PlanoDeContaFabricaTest extends FabricaTest {
	private static PlanoDeContaFabricaTest instance = null;

	public static synchronized PlanoDeContaFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeContaFabricaTest();
		}
		return instance;
	}

	public PlanoDeConta criaPlanoDeContas() {
		PlanoDeConta p = new PlanoDeConta();
		p.setAtivo(Boolean.TRUE);
		p.setCodigo(Long.valueOf(criarStringDinamicaPorTamanho(10)));
		p.setCompartilhado(Boolean.TRUE);
		//
		p.setItemApenasDeGrupo(Boolean.TRUE);
		p.setItemPermiteDesconto(Boolean.TRUE);
		p.setItemReferenteAMensalidades(Boolean.TRUE);
		p.setItemSujeitoAISS(Boolean.TRUE);
		p.setNome("nome" + Math.random() * 10000);
		p.setOutrosDetalhes("outrosDetalhes");
		p.setTipoDePlanoDeContas(EnumTipoDePlanoDeContas.ENTRADA);
		return p;
	}

	public PlanoDeConta criaPlanoDeContasPersistido(EntityManager em) {
		PlanoDeConta p = criaPlanoDeContas();
		PlanoDeContaDao dao = new PlanoDeContaDao(em);
		//
		dao.persist(p);
		return p;
	}
}
