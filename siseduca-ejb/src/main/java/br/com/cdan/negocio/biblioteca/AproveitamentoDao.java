package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;

public class AproveitamentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AproveitamentoDao(EntityManager em) {
		setEntityManager(em);
	}

	public AproveitamentoDao() {
	}

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
