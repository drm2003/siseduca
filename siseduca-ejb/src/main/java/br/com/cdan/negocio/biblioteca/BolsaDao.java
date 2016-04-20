package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Bolsa;

public class BolsaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Bolsa bolsa = (Bolsa) obj;
		bolsa.setAtivo(false);
		getEntityManager().merge(bolsa);
	}
}
