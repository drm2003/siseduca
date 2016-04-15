package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Cidade;

public class CidadeDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Cidade cidade = (Cidade) obj;
		cidade.setAtivo(false);
		getEntityManager().merge(cidade);
	}
}
