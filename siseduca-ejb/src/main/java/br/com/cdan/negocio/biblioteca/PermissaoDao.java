package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.Permissao;

public class PermissaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PermissaoDao() {

	}

	public PermissaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Permissao permissao = (Permissao) obj;
		permissao.setAtivo(false);
		getEntityManager().merge(permissao);
	}
}
