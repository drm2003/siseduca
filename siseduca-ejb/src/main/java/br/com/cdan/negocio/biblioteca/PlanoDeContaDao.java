package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.PlanoDeConta;

public class PlanoDeContaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
