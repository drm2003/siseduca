package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.CalendarioLetivo;

public class CalendarioLetivoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CalendarioLetivoDao() {
		// TODO Auto-generated constructor stub
	}

	public CalendarioLetivoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CalendarioLetivo calendarioLetivo = (CalendarioLetivo) obj;
		calendarioLetivo.setAtivo(false);
		getEntityManager().merge(calendarioLetivo);
	}
}
