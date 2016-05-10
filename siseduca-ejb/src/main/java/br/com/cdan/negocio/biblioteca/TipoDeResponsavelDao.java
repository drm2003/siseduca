package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeResponsavel;

public class TipoDeResponsavelDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeResponsavelDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeResponsavel tipoDeResponsavel = (TipoDeResponsavel) obj;
		tipoDeResponsavel.setAtivo(false);
		getEntityManager().merge(tipoDeResponsavel);
	}
}
