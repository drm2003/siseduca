package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.AlunoInteressado;

public class AlunoInteressadoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AlunoInteressadoDao(EntityManager em) {
		setEntityManager(em);
	}

	public AlunoInteressadoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		AlunoInteressado a = (AlunoInteressado) obj;
		a.setAtivo(Boolean.FALSE);
		getEntityManager().merge(a);
	}
}
