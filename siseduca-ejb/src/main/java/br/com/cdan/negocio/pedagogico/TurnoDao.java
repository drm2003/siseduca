package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.Turno;

public class TurnoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TurnoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Turno t = (Turno) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
