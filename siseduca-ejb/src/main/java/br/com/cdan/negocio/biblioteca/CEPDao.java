package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.cep.CEP;

public class CEPDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CEP cep = (CEP) obj;
		cep.setAtivo(false);
		getEntityManager().merge(cep);
	}
}
