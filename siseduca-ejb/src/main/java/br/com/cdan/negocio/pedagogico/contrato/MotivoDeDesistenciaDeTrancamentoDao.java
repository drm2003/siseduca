package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.MotivoDeDesistenciaDeTrancamento;

public class MotivoDeDesistenciaDeTrancamentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeDesistenciaDeTrancamentoDao() {
	}

	public MotivoDeDesistenciaDeTrancamentoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MotivoDeDesistenciaDeTrancamento m = (MotivoDeDesistenciaDeTrancamento) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
