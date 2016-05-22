package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;

public class TipoDeContratoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeContratoDao() {
	}

	public TipoDeContratoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeContrato tipoDeContato = (TipoDeContrato) obj;
		tipoDeContato.setAtivo(false);
		getEntityManager().merge(tipoDeContato);
	}
}
