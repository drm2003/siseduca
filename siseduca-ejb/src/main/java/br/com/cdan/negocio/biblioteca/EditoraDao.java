package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Editora;

public class EditoraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EditoraDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Editora editora = (Editora) obj;
		editora.setAtivo(false);
		getEntityManager().merge(editora);
	}
}
