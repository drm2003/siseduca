package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Dependencia;

public class DependenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DependenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Dependencia dependencia = (Dependencia) obj;
		dependencia.setAtivo(false);
		getEntityManager().merge(dependencia);
	}
}
