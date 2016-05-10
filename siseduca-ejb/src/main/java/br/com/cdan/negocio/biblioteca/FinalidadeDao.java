package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.Finalidade;

public class FinalidadeDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public FinalidadeDao(EntityManager em) {
		setEntityManager(em);
	}

	public FinalidadeDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Finalidade finalidade = (Finalidade) obj;
		finalidade.setAtivo(false);
		getEntityManager().merge(finalidade);
	}
}
