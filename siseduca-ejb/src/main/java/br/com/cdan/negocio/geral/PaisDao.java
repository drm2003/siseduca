package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.cep.Pais;

public class PaisDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public PaisDao() {
		// TODO Auto-generated constructor stub
	}

	public PaisDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Pais pais = (Pais) obj;
		pais.setAtivo(false);
		getEntityManager().merge(pais);
	}
}
