package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContasAReceber;

public class ContasAReceberDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContasAReceber contasAReceber = (ContasAReceber) obj;
		contasAReceber.setAtivo(false);
		getEntityManager().merge(contasAReceber);
	}
}
