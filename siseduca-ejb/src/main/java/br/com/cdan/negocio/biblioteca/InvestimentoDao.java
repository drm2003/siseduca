package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Investimento;

public class InvestimentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Investimento investimento = (Investimento) obj;
		investimento.setAtivo(false);
		getEntityManager().merge(investimento);
	}
}
