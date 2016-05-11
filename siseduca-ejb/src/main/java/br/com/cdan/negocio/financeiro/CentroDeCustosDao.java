package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.CentroDeCustos;

public class CentroDeCustosDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CentroDeCustosDao() {
		// TODO Auto-generated constructor stub
	}

	public CentroDeCustosDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CentroDeCustos centroDeCustos = (CentroDeCustos) obj;
		centroDeCustos.setAtivo(false);
		getEntityManager().merge(centroDeCustos);
	}
}
