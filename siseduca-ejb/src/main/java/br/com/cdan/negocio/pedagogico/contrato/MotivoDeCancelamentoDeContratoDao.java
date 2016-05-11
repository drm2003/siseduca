package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contrato.MotivoDeCancelamentoDeContrato;

public class MotivoDeCancelamentoDeContratoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeCancelamentoDeContratoDao() {
	}

	public MotivoDeCancelamentoDeContratoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MotivoDeCancelamentoDeContrato m = (MotivoDeCancelamentoDeContrato) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
