package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Categoria;

public class CategoriaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Categoria categoria = (Categoria) obj;
		categoria.setAtivo(false);
		getEntityManager().merge(categoria);
	}
}
