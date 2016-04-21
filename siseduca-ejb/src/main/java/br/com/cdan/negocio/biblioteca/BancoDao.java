package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Banco;

public class BancoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Banco banco = (Banco) obj;
		banco.setAtivo(false);
		getEntityManager().merge(banco);
	}
}
