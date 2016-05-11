package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;

public class EstagioMonografiaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EstagioMonografiaDao() {
		// TODO Auto-generated constructor stub
	}

	public EstagioMonografiaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		EstagioMonografia estagioMonografia = (EstagioMonografia) obj;
		estagioMonografia.setAtivo(false);
		getEntityManager().merge(estagioMonografia);
	}
}
