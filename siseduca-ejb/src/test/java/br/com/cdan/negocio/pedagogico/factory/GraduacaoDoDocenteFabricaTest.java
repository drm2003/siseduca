package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.GraduacaoDoDocente;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.GraduacaoDoDocenteDao;

public class GraduacaoDoDocenteFabricaTest extends FabricaTest {
	private static GraduacaoDoDocenteFabricaTest instance = null;

	public static synchronized GraduacaoDoDocenteFabricaTest getInstance() {
		if (instance == null) {
			instance = new GraduacaoDoDocenteFabricaTest();
		}
		return instance;
	}

	public GraduacaoDoDocente criaGraduacaoDoDocente() {
		GraduacaoDoDocente g = new GraduacaoDoDocente();
		g.setAtivo(Boolean.TRUE);
		g.setDescricao(criarStringDinamicaPorTamanho(100));
		return g;
	}

	public GraduacaoDoDocente criaGraduacaoDoDocentePersistido(EntityManager em) {
		GraduacaoDoDocente g = new GraduacaoDoDocente();
		GraduacaoDoDocenteDao dao = new GraduacaoDoDocenteDao(em);
		dao.persist(g);
		return g;
	}
}
