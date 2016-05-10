package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Caixa;

public class CaixaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CaixaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Caixa caixa = (Caixa) obj;
		caixa.setAtivo(false);
		getEntityManager().merge(caixa);
	}
}
