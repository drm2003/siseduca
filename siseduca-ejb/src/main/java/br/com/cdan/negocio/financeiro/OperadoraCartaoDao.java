package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.OperadoraCartao;

public class OperadoraCartaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OperadoraCartaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		OperadoraCartao o = (OperadoraCartao) obj;
		o.setAtivo(false);
		getEntityManager().merge(o);
	}
}
