package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.DadoBancario;

public class DadoBancarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DadoBancarioDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DadoBancario dadoBancario = (DadoBancario) obj;
		dadoBancario.setAtivo(false);
		getEntityManager().merge(dadoBancario);
	}
}
