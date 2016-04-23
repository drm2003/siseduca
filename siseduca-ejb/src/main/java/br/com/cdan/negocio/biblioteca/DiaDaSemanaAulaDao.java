package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;

public class DiaDaSemanaAulaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DiaDaSemanaAula diaDaSemanaAula = (DiaDaSemanaAula) obj;
		diaDaSemanaAula.setAtivo(false);
		getEntityManager().merge(diaDaSemanaAula);
	}
}
