package br.com.cdan.negocio.acesso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.Usuario;

public class UsuarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public UsuarioDao() {
	}

	public UsuarioDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Usuario usuario = (Usuario) obj;
		usuario.setAtivo(false);
		getEntityManager().merge(usuario);
	}
}