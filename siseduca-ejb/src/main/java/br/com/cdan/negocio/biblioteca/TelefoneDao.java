package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Telefone;

public class TelefoneDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TelefoneDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Telefone telefone = (Telefone) obj;
		telefone.setAtivo(false);
		getEntityManager().merge(telefone);
	}
}
