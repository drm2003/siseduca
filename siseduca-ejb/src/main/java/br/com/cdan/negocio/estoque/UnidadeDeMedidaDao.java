package br.com.cdan.negocio.estoque;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.UnidadeDeMedida;

public class UnidadeDeMedidaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public UnidadeDeMedidaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		UnidadeDeMedida u = (UnidadeDeMedida) obj;
		u.setAtivo(false);
		getEntityManager().merge(u);
	}
}
