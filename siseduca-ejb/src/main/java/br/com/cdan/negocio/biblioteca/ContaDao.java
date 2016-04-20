package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Conta;

public class ContaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Conta conta = (Conta) obj;
		conta.setAtivo(false);
		getEntityManager().merge(conta);
	}
}
