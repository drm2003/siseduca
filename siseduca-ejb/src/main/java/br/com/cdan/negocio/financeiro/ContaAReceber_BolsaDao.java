package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContaAReceber_Bolsa;

public class ContaAReceber_BolsaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ContaAReceber_BolsaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContaAReceber_Bolsa contasAReceber_Bolsa = (ContaAReceber_Bolsa) obj;
		contasAReceber_Bolsa.setAtivo(false);
		getEntityManager().merge(contasAReceber_Bolsa);
	}
}
