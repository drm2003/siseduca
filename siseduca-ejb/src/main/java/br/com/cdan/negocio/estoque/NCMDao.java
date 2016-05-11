package br.com.cdan.negocio.estoque;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.NCM;

public class NCMDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public NCMDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		NCM ncm = (NCM) obj;
		ncm.setAtivo(false);
		getEntityManager().merge(ncm);
	}
}
