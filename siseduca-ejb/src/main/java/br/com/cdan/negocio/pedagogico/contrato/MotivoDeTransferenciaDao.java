package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;

public class MotivoDeTransferenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeTransferenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	public MotivoDeTransferenciaDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MotivoDeTransferencia m = (MotivoDeTransferencia) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
