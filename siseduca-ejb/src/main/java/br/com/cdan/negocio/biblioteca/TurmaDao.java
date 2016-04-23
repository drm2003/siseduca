package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Turma;

public class TurmaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Turma turma = (Turma) obj;
		turma.setAtivo(false);
		getEntityManager().merge(turma);
	}
}
