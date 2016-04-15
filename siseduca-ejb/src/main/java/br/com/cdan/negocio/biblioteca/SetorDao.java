package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Editora;

public class SetorDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Editora editora = (Editora) obj;
		editora.setAtivo(false);
		getEntityManager().merge(editora);
	}
}
