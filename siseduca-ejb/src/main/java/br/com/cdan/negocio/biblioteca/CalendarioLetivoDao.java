package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.CalendarioLetivo;

public class CalendarioLetivoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
