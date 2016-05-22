package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Grupo;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.GrupoDao;

public class GrupoFabricaTest extends FabricaTest {
	private static GrupoFabricaTest instance = null;

	public static synchronized GrupoFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrupoFabricaTest();
		}
		return instance;
	}

	public Grupo criaGrupo() {
		Grupo g = new Grupo();
		g.setAtivo(Boolean.TRUE);
		g.setDescricao(criarStringDinamicaPorTamanho(100));
		//
		g.setPeso(Long.valueOf(1));
		return g;
	}

	public Grupo criaGrupoPersistido(EntityManager em) {
		Grupo g = criaGrupo();
		GrupoDao dao = new GrupoDao(em);
		//
		dao.persist(g);
		return g;
	}
}
