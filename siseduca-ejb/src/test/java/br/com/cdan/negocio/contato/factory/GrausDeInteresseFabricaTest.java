package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.GrausDeInteresse;
import br.com.cdan.negocio.contato.GrausDeInteresseDao;

public class GrausDeInteresseFabricaTest {
	private static GrausDeInteresseFabricaTest instance = null;

	public static synchronized GrausDeInteresseFabricaTest getInstance() {
		if (instance == null) {
			instance = new GrausDeInteresseFabricaTest();
		}
		return instance;
	}

	public GrausDeInteresse criaGrausDeInteresse() {
		GrausDeInteresse g = new GrausDeInteresse();
		g.setAtivo(Boolean.TRUE);
		g.setDescricao("descricao" + Math.random() * 10000);
		//
		return g;
	}

	public GrausDeInteresse criaGrausDeInteressePersistido(EntityManager em) {
		GrausDeInteresse g = criaGrausDeInteresse();
		GrausDeInteresseDao dao = new GrausDeInteresseDao(em);
		//
		dao.persist(g);
		return g;
	}
}
