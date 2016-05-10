package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Transferencia;

public class TransferenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TransferenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Transferencia transferencia = (Transferencia) obj;
		transferencia.setAtivo(false);
		getEntityManager().merge(transferencia);
	}
}
