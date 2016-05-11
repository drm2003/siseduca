package br.com.cdan.negocio.acesso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.GrupoDeUsuarios;

public class GrupoDeUsuariosDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public GrupoDeUsuariosDao(EntityManager em) {
		setEntityManager(em);
	}

	public GrupoDeUsuariosDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		GrupoDeUsuarios grupoDeUsuarios = (GrupoDeUsuarios) obj;
		grupoDeUsuarios.setAtivo(false);
		getEntityManager().merge(grupoDeUsuarios);
	}
}
