package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Autor;

public class AutorDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Autor autor = (Autor) obj;
		autor.setAtivo(false);
		getEntityManager().merge(autor);
	}
}
