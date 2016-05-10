package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Origem;

public class OrigemDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OrigemDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Origem origem = (Origem) obj;
		origem.setAtivo(false);
		getEntityManager().merge(origem);
	}
}
