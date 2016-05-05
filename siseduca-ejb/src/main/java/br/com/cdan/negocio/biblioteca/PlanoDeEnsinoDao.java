package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;

public class PlanoDeEnsinoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PlanoDeEnsinoDao(EntityManager em) {
		setEntityManager(em);
	}

	public PlanoDeEnsinoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		PlanoDeEnsino planoDeEnsino = (PlanoDeEnsino) obj;
		planoDeEnsino.setAtivo(false);
		getEntityManager().merge(planoDeEnsino);
	}
}
