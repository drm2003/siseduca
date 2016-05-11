package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Desconto;

public class DescontoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DescontoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Desconto desconto = (Desconto) obj;
		desconto.setAtivo(false);
		getEntityManager().merge(desconto);
	}
}
