package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.Grupo;

public class GrupoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public GrupoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Grupo g = (Grupo) obj;
		g.setAtivo(false);
		getEntityManager().merge(g);
	}
}
