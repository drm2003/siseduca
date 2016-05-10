package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Curso;

public class CursoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CursoDao() {
	}

	public CursoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Curso curso = (Curso) obj;
		curso.setAtivo(false);
		getEntityManager().merge(curso);
	}
}
