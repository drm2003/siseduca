package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Obra;

public class ObraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ObraDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Obra obra = (Obra) obj;
		obra.setAtivo(false);
		getEntityManager().merge(obra);
	}
}
