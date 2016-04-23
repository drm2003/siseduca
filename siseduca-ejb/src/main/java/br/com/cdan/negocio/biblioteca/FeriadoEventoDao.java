package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.FeriadoEvento;

public class FeriadoEventoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		FeriadoEvento feriadoEvento = (FeriadoEvento) obj;
		feriadoEvento.setAtivo(false);
		getEntityManager().merge(feriadoEvento);
	}
}
