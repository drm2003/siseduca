package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.GrupoDeHabilidades;

public class GrupoDeHabilidadesDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public GrupoDeHabilidadesDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		GrupoDeHabilidades g = (GrupoDeHabilidades) obj;
		g.setAtivo(false);
		getEntityManager().merge(g);
	}
}
