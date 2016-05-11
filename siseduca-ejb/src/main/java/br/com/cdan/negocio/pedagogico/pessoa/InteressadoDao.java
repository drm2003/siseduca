package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Interessado;

public class InteressadoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public InteressadoDao() {
		// TODO Auto-generated constructor stub
	}

	public InteressadoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Interessado interessado = (Interessado) obj;
		interessado.setAtivo(false);
		getEntityManager().merge(interessado);
	}
}
