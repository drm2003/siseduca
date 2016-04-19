package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;

public class AproveitamentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Aproveitamento aproveitamento = (Aproveitamento) obj;
		aproveitamento.setAtivo(false);
		getEntityManager().merge(aproveitamento);
	}
}
