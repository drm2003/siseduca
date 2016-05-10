package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Idioma;

public class IdiomaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public IdiomaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Idioma idioma = (Idioma) obj;
		idioma.setAtivo(false);
		getEntityManager().merge(idioma);
	}
}
