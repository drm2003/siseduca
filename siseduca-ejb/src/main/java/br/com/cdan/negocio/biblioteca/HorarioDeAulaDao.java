package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.HorarioDeAula;

public class HorarioDeAulaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		HorarioDeAula horarioDeAula = (HorarioDeAula) obj;
		horarioDeAula.setAtivo(false);
		getEntityManager().merge(horarioDeAula);
	}
}
