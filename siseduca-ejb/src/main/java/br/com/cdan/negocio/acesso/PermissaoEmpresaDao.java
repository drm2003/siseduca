package br.com.cdan.negocio.acesso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Pessoa;

public class PermissaoEmpresaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PermissaoEmpresaDao() {

	}

	public PermissaoEmpresaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Pessoa pessoa = (Pessoa) obj;
		pessoa.setAtivo(false);
		getEntityManager().merge(pessoa);
	}
}
