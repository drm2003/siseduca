package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.AreasDeConhecimento;

public class AreasDeConhecimentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		AreasDeConhecimento areas = (AreasDeConhecimento) obj;
		areas.setAtivo(false);
		getEntityManager().merge(areas);
	}
}
