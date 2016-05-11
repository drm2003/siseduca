package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.CFOP;

public class CFOPDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CFOPDao() {
		// TODO Auto-generated constructor stub
	}

	public CFOPDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CFOP cfop = (CFOP) obj;
		cfop.setAtivo(false);
		getEntityManager().merge(cfop);
	}
}
