package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.TipoDeDisciplina;

public class TipoDeDisciplinaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeDisciplinaDao() {

	}

	public TipoDeDisciplinaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeDisciplina tipoDeDisciplina = (TipoDeDisciplina) obj;
		tipoDeDisciplina.setAtivo(false);
		getEntityManager().merge(tipoDeDisciplina);
	}
}
