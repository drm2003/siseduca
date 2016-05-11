package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;

public class SistemaDeAvaliacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SistemaDeAvaliacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	public SistemaDeAvaliacaoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SistemaDeAvaliacao sistemaDeAvaliacao = (SistemaDeAvaliacao) obj;
		sistemaDeAvaliacao.setAtivo(false);
		getEntityManager().merge(sistemaDeAvaliacao);
	}
}
