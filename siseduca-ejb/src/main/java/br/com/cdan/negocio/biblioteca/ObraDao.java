package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.Setor;

public class ObraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Setor setor = (Setor) obj;
		setor.setAtivo(false);
		getEntityManager().merge(setor);
	}
}
