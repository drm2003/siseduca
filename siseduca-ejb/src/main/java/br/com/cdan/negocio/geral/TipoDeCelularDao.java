package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeCelular;

public class TipoDeCelularDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeCelularDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeCelular tipoDeCelular = (TipoDeCelular) obj;
		tipoDeCelular.setAtivo(false);
		getEntityManager().merge(tipoDeCelular	);
	}
}
