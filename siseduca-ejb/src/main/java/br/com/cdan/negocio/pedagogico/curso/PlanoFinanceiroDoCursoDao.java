package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;

public class PlanoFinanceiroDoCursoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PlanoFinanceiroDoCursoDao(EntityManager em) {
		setEntityManager(em);
	}

	public PlanoFinanceiroDoCursoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		PlanoFinanceiroDoCurso planoFinanceiroDoCurso = (PlanoFinanceiroDoCurso) obj;
		planoFinanceiroDoCurso.setAtivo(false);
		getEntityManager().merge(planoFinanceiroDoCurso);
	}
}
