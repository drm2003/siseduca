package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.DadosDoExemplar;

public class DadosDoExemplarDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DadosDoExemplar dadosDoExemplar = (DadosDoExemplar) obj;
		dadosDoExemplar.setAtivo(false);
		getEntityManager().merge(dadosDoExemplar);
	}
}
