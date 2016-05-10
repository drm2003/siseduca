package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.EstadoCivil;

public class EstadoCivilDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EstadoCivilDao() {
		// TODO Auto-generated constructor stub
	}

	public EstadoCivilDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		EstadoCivil estadoCivil = (EstadoCivil) obj;
		estadoCivil.setAtivo(false);
		getEntityManager().merge(estadoCivil);
	}
}
