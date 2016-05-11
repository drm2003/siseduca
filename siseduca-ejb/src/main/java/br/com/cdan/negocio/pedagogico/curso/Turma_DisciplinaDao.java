package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

public class Turma_DisciplinaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public Turma_DisciplinaDao(EntityManager em) {
		setEntityManager(em);
	}

	public Turma_DisciplinaDao() {
	}

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
