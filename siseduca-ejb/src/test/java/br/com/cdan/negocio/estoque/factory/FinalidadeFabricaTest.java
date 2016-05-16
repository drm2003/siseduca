package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Finalidade;
import br.com.cdan.negocio.estoque.FinalidadeDao;

public class FinalidadeFabricaTest {
	private static FinalidadeFabricaTest instance = null;

	public static synchronized FinalidadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new FinalidadeFabricaTest();
		}
		return instance;
	}

	public Finalidade criaFinalidade() {
		Finalidade f = new Finalidade();
		f.setAtivo(Boolean.TRUE);
		f.setDescricao("descricao");
		//
		return f;
	}

	public Finalidade criaFinalidadePersistido(EntityManager em) {
		Finalidade f = criaFinalidade();
		FinalidadeDao dao = new FinalidadeDao(em);
		//
		dao.persist(f);
		return f;
	}

}
