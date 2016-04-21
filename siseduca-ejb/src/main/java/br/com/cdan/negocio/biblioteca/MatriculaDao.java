package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Interessado;

public class MatriculaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Interessado interessado = (Interessado) obj;
		interessado.setAtivo(false);
		getEntityManager().merge(interessado);
	}
}
