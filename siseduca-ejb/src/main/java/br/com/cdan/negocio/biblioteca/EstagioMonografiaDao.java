package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;

public class EstagioMonografiaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		EstagioMonografia estagioMonografia = (EstagioMonografia) obj;
		estagioMonografia.setAtivo(false);
		getEntityManager().merge(estagioMonografia);
	}
}
