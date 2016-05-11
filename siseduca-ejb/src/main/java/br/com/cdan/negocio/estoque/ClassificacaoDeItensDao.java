package br.com.cdan.negocio.estoque;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.ClassificacaoDeItens;

public class ClassificacaoDeItensDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ClassificacaoDeItensDao() {
		// TODO Auto-generated constructor stub
	}

	public ClassificacaoDeItensDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ClassificacaoDeItens classificacaoDeItens = (ClassificacaoDeItens) obj;
		classificacaoDeItens.setAtivo(false);
		getEntityManager().merge(classificacaoDeItens);
	}
}
