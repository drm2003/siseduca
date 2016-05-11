package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.FeriadoEvento;

public class FeriadoEventoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public FeriadoEventoDao() {
		// TODO Auto-generated constructor stub
	}

	public FeriadoEventoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		FeriadoEvento feriadoEvento = (FeriadoEvento) obj;
		feriadoEvento.setAtivo(false);
		getEntityManager().merge(feriadoEvento);
	}
}
