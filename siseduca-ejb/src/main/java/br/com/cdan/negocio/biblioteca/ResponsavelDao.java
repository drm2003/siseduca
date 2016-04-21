package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Responsavel;

public class ResponsavelDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Responsavel responsavel = (Responsavel) obj;
		responsavel.setAtivo(false);
		getEntityManager().merge(responsavel);
	}
}
