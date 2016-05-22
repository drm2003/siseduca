package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.curso.PlanoDeEnsinoDao;

public class PlanoDeEnsinoFabricaTest extends FabricaTest {
	private static PlanoDeEnsinoFabricaTest instance = null;

	public static synchronized PlanoDeEnsinoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoDeEnsinoFabricaTest();
		}
		return instance;
	}

	public PlanoDeEnsino criaPlanoDeEnsino(EntityManager em) {
		PlanoDeEnsino p = new PlanoDeEnsino();
		p.setAtivo(Boolean.TRUE);
		p.setContribuicaoParaAInstituicao(criarStringDinamicaPorTamanho(100));
		p.setContribuicaoParaOEgresso(criarStringDinamicaPorTamanho(100));
		p.setEmenta(criarStringDinamicaPorTamanho(100));
		p.setInterdisciplinariedade(criarStringDinamicaPorTamanho(100));
		p.setObjetivosEspecificos(criarStringDinamicaPorTamanho(100));
		p.setObjetivosGerais(criarStringDinamicaPorTamanho(100));
		return p;
	}

	public PlanoDeEnsino criaPlanoDeEnsinoPersistido(EntityManager em) {
		PlanoDeEnsino p = criaPlanoDeEnsino(em);
		PlanoDeEnsinoDao dao = new PlanoDeEnsinoDao(em);
		//
		dao.persist(p);
		return p;
	}
}