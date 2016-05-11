package br.com.cdan.negocio.contato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contato.GrausDeInteresse;

public class GrausDeInteresseDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public GrausDeInteresseDao(EntityManager em) {
		setEntityManager(em);
	}

	public GrausDeInteresseDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		GrausDeInteresse grausDeInteresse = (GrausDeInteresse) obj;
		grausDeInteresse.setAtivo(false);
		getEntityManager().merge(grausDeInteresse);
	}
}
