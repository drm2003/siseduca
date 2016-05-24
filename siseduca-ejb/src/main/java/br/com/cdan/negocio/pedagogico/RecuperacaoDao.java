package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.Recuperacao;

public class RecuperacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public RecuperacaoDao() {
		// TODO Auto-generated constructor stub
	}

	public RecuperacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Recuperacao r = (Recuperacao) obj;
		r.setAtivo(false);
		getEntityManager().merge(r);
	}
}
