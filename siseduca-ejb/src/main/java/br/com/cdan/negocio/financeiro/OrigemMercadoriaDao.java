package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.OrigemMercadoria;

public class OrigemMercadoriaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OrigemMercadoriaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		OrigemMercadoria o = (OrigemMercadoria) obj;
		o.setAtivo(false);
		getEntityManager().merge(o);
	}
}
