package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;
import br.com.cdan.model.pedagogico.GrupoDeHabilidades;
import br.com.cdan.negocio.pedagogico.DescricaoDeHabilidadesDao;
import br.com.cdan.negocio.pedagogico.GrupoDeHabilidadesDao;

public class GrupoDeHabilidadesFabricaTest {
	private static GrupoDeHabilidadesFabricaTest instance = null;

	public static synchronized GrupoDeHabilidadesFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrupoDeHabilidadesFabricaTest();
		}
		return instance;
	}

	public GrupoDeHabilidades criaGrupoDeHabilidades() {
		GrupoDeHabilidades g = new GrupoDeHabilidades();
		g.setAtivo(Boolean.TRUE);
		g.setDescricao("descricao");
		g.setCompartilhado(Boolean.TRUE);
		g.setDescricaoDeHabilidades(DescricaoDeHabilidadesFabricaTest.getInstance().criaDescricaoDeHabilidades());
		g.setOrdem(Long.valueOf(2));
		g.setPeso(Long.valueOf(1));
		return g;
	}

	public GrupoDeHabilidades criaGrupoDeHabilidadesPersistido(EntityManager em) {
		GrupoDeHabilidades g = criaGrupoDeHabilidades();
		GrupoDeHabilidadesDao dao = new GrupoDeHabilidadesDao(em);
		//
		DescricaoDeHabilidadesDao descricaoDeHabilidadesDao = new DescricaoDeHabilidadesDao(em);
		DescricaoDeHabilidades descricaoDeHabilidades = g.getDescricaoDeHabilidades();
		descricaoDeHabilidadesDao.persist(descricaoDeHabilidades);
		g.setDescricaoDeHabilidades(descricaoDeHabilidades);
		//
		dao.persist(descricaoDeHabilidades);
		return g;
	}
}
