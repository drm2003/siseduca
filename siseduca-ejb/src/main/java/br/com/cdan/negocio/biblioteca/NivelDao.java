package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Nivel;

public class NivelDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public NivelDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Nivel nivel = (Nivel) obj;
		nivel.setAtivo(false);
		getEntityManager().merge(nivel);
	}
}
