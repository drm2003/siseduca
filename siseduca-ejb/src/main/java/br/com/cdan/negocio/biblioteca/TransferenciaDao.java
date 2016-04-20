package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Transferencia;

public class TransferenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
