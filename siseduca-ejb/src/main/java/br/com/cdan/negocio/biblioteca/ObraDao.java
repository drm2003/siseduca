package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Obra;

public class ObraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Obra obra = (Obra) obj;
		obra.setAtivo(false);
		getEntityManager().merge(obra);
	}
}
