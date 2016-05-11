package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.PlanoDeConta;

public class PlanoDeContaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PlanoDeContaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		PlanoDeConta planoDeconta = (PlanoDeConta) obj;
		planoDeconta.setAtivo(false);
		getEntityManager().merge(planoDeconta);
	}
}
