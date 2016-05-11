package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Bairro;

public class BairroDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public BairroDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Bairro bairro = (Bairro) obj;
		bairro.setAtivo(false);
		getEntityManager().merge(bairro);
	}
}
