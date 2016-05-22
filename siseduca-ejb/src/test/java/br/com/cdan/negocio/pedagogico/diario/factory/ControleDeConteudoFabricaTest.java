package br.com.cdan.negocio.pedagogico.diario.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.diario.ControleDeConteudo;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.diario.ControleDeConteudoDao;

public class ControleDeConteudoFabricaTest extends FabricaTest {
	private static ControleDeConteudoFabricaTest instance = null;

	public static synchronized ControleDeConteudoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ControleDeConteudoFabricaTest();
		}
		return instance;
	}

	public ControleDeConteudo criaControleDeConteudo() {
		ControleDeConteudo c = new ControleDeConteudo();
		c.setConteudo(criarStringDinamicaPorTamanho(100));
		c.setAtivo(Boolean.TRUE);
		return c;
	}

	public ControleDeConteudo criaControleDeConteudoPersistido(EntityManager em) {
		ControleDeConteudo c = criaControleDeConteudo();
		ControleDeConteudoDao controleDeConteudoDao = new ControleDeConteudoDao(em);
		controleDeConteudoDao.persist(c);
		return c;
	}
}
