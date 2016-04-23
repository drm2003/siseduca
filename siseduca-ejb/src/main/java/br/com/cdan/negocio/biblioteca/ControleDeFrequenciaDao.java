package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;

public class ControleDeFrequenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ControleDeFrequencia controleDeFrequencia = (ControleDeFrequencia) obj;
		controleDeFrequencia.setAtivo(false);
		getEntityManager().merge(controleDeFrequencia);
	}
}
