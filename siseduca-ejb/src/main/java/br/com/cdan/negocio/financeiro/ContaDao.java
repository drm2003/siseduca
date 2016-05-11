package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Conta;

public class ContaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ContaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Conta conta = (Conta) obj;
		conta.setAtivo(false);
		getEntityManager().merge(conta);
	}
}
