package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.LinhaProgramatica;

public class LinhaProgramaticaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public LinhaProgramaticaDao() {
		// TODO Auto-generated constructor stub
	}

	public LinhaProgramaticaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		LinhaProgramatica linhaProgramatica = (LinhaProgramatica) obj;
		linhaProgramatica.setAtivo(false);
		getEntityManager().merge(linhaProgramatica);
	}
}
