package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.CST;

public class CSTDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CSTDao() {
		// TODO Auto-generated constructor stub
	}

	public CSTDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CST c = (CST) obj;
		c.setAtivo(false);
		getEntityManager().merge(c);
	}
}
