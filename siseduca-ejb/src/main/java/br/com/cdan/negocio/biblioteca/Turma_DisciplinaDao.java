package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

public class Turma_DisciplinaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Turma_Disciplina turmaDisciplina = (Turma_Disciplina) obj;
		turmaDisciplina.setAtivo(false);
		getEntityManager().merge(turmaDisciplina);
	}
}
