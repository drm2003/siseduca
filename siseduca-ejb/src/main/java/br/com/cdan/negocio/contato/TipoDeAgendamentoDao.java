package br.com.cdan.negocio.contato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contato.TipoDeAgendamento;

public class TipoDeAgendamentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeAgendamentoDao() {
	}

	public TipoDeAgendamentoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeAgendamento tipoDeAgendamento = (TipoDeAgendamento) obj;
		tipoDeAgendamento.setAtivo(false);
		getEntityManager().merge(tipoDeAgendamento);
	}
}
