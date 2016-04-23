package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContaAReceber;

public class ContaAReceberDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContaAReceber contaAReceber = (ContaAReceber) obj;
		contaAReceber.setAtivo(false);
		getEntityManager().merge(contaAReceber);
	}
}
