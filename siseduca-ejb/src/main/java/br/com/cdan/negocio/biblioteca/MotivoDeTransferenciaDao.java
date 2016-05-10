package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;

public class MotivoDeTransferenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeTransferenciaDao() {
	}

	public MotivoDeTransferenciaDao(EntityManager em) {
		setEntityManager(em);
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
