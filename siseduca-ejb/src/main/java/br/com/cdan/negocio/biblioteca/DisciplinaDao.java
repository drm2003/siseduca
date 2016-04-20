package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Disciplina;

public class DisciplinaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Disciplina disciplina = (Disciplina) obj;
		disciplina.setAtivo(false);
		getEntityManager().merge(disciplina);
	}
}
