package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Desconto;

public class DescontoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Desconto desconto = (Desconto) obj;
		desconto.setAtivo(false);
		getEntityManager().merge(desconto);
	}
}
