package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;

public class DiarioDeAulaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DiarioDeAula diarioDeAula = (DiarioDeAula) obj;
		diarioDeAula.setAtivo(false);
		getEntityManager().merge(diarioDeAula);
	}
}
