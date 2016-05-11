package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.AvaliacaoPadrao;

public class AvaliacaoPadraoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AvaliacaoPadraoDao() {
		// TODO Auto-generated constructor stub
	}

	public AvaliacaoPadraoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		AvaliacaoPadrao a = (AvaliacaoPadrao) obj;
		a.setAtivo(false);
		getEntityManager().merge(a);
	}
}
