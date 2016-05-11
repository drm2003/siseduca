package br.com.cdan.negocio.geral.cep;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.cep.Logradouro;

public class LogradouroDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public LogradouroDao() {
	}

	public LogradouroDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Logradouro l = (Logradouro) obj;
		l.setAtivo(false);
		getEntityManager().merge(l);
	}
}
