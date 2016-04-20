package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Telefone;

public class PlanoDeContasDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Telefone telefone = (Telefone) obj;
		telefone.setAtivo(false);
		getEntityManager().merge(telefone);
	}
}
