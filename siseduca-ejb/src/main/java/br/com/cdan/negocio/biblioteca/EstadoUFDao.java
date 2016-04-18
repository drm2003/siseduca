package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.EstadoUF;

public class EstadoUFDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		EstadoUF estadoUF = (EstadoUF) obj;
		estadoUF.setAtivo(false);
		getEntityManager().merge(estadoUF);
	}
}
