package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Email;

public class EmailDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EmailDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Email email = (Email) obj;
		email.setAtivo(false);
		getEntityManager().merge(email);
	}
}
