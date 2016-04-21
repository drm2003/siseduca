package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContasAReceber_Bolsa;

public class ContasAReceber_BolsaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContasAReceber_Bolsa contasAReceber_Bolsa = (ContasAReceber_Bolsa) obj;
		contasAReceber_Bolsa.setAtivo(false);
		getEntityManager().merge(contasAReceber_Bolsa);
	}
}
