package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Follow;

public class FollowDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public FollowDao(EntityManager em) {
		setEntityManager(em);
	}

	public FollowDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Follow follow = (Follow) obj;
		follow.setAtivo(false);
		getEntityManager().merge(follow);
	}
}
