package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.Usuario;

public class UsuarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
