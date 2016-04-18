package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Pessoa;

public class PessoaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Pessoa pessoa = (Pessoa) obj;
		pessoa.setAtivo(false);
		getEntityManager().merge(pessoa);
	}
}
