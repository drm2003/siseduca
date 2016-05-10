package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;

public class SituacaoDoContratoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public SituacaoDoContratoDao() {
	}

	public SituacaoDoContratoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SituacaoDoContrato situacaoDoContrato = (SituacaoDoContrato) obj;
		situacaoDoContrato.setAtivo(false);
		getEntityManager().merge(situacaoDoContrato);
	}
}
