package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Metodologia;

public class MetodologiaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MetodologiaDao() {
	}

	public MetodologiaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Metodologia metodologia = (Metodologia) obj;
		metodologia.setAtivo(false);
		getEntityManager().merge(metodologia);
	}
}
