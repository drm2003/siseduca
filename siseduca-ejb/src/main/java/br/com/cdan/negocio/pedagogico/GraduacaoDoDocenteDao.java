package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.GraduacaoDoDocente;

public class GraduacaoDoDocenteDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public GraduacaoDoDocenteDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		GraduacaoDoDocente g = (GraduacaoDoDocente) obj;
		g.setAtivo(false);
		getEntityManager().merge(g);
	}
}
