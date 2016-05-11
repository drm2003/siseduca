package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.TipoDePagamentoProfessorHorista;

public class TipoDePagamentoProfessorHoristaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDePagamentoProfessorHoristaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDePagamentoProfessorHorista t = (TipoDePagamentoProfessorHorista) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
