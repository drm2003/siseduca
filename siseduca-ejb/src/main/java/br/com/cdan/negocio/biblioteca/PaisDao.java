package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Pais;

public class PaisDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Pais pais = (Pais) obj;
		pais.setAtivo(false);
		getEntityManager().merge(pais);
	}
}
