package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.TipoDeObra;

public class TipoDeObraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeObra tipoDeObra = (TipoDeObra) obj;
		tipoDeObra.setAtivo(false);
		getEntityManager().merge(tipoDeObra);
	}
}
