package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.GrupoDeHabilidades;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.GrupoDeHabilidadesDao;

public class GrupoDeHabilidadesFabricaTest extends FabricaTest {
	private static GrupoDeHabilidadesFabricaTest instance = null;

	public static synchronized GrupoDeHabilidadesFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrupoDeHabilidadesFabricaTest();
		}
		return instance;
	}

	public GrupoDeHabilidades criaGrupoDeHabilidades(EntityManager em) {
		GrupoDeHabilidades g = new GrupoDeHabilidades();
		g.setAtivo(Boolean.TRUE);
		g.setCompartilhado(Boolean.TRUE);
		g.setDescricao(criarStringDinamicaPorTamanho(100));

		g.setDescricaoDeHabilidades(
				DescricaoDeHabilidadesFabricaTest.getInstance().criaDescricaoDeHabilidadesPersistido(em));

		g.setOrdem(Long.valueOf(1));
		g.setPeso(Long.valueOf(2));
		return g;
	}

	public GrupoDeHabilidades criaGrupoDeHabilidadesPersistido(EntityManager em) {
		GrupoDeHabilidades g = criaGrupoDeHabilidades(em);
		GrupoDeHabilidadesDao dao = new GrupoDeHabilidadesDao(em);
		dao.persist(g);
		return g;
	}
}
