package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContaAPagar;

public class ContaAPagarDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ContaAPagarDao(EntityManager em) {
		setEntityManager(em);
	}

	public ContaAPagarDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContaAPagar contasAPagar = (ContaAPagar) obj;
		contasAPagar.setAtivo(false);
		getEntityManager().merge(contasAPagar);
	}
}
