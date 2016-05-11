package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.PlanoDeContas_CentroDeCustos;

public class PlanoDeContas_CentroDeCustosDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PlanoDeContas_CentroDeCustosDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		PlanoDeContas_CentroDeCustos planoDecontasCentroDeCustos = (PlanoDeContas_CentroDeCustos) obj;
		planoDecontasCentroDeCustos.setAtivo(false);
		getEntityManager().merge(planoDecontasCentroDeCustos);
	}
}
