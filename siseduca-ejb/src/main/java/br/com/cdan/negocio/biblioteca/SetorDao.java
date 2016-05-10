package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Setor;

public class SetorDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SetorDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Setor setor = (Setor) obj;
		setor.setAtivo(false);
		getEntityManager().merge(setor);
	}
}
