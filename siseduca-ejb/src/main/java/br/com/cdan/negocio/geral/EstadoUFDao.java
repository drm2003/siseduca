package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.cep.EstadoUF;

public class EstadoUFDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EstadoUFDao() {
		// TODO Auto-generated constructor stub
	}

	public EstadoUFDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		EstadoUF estadoUF = (EstadoUF) obj;
		estadoUF.setAtivo(false);
		getEntityManager().merge(estadoUF);
	}
}
