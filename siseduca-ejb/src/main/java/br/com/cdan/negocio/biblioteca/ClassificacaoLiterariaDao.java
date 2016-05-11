package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;

public class ClassificacaoLiterariaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ClassificacaoLiterariaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ClassificacaoLiteraria c = (ClassificacaoLiteraria) obj;
		c.setAtivo(false);
		getEntityManager().merge(c);
	}
}
