package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Cargo;

public class CargoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CargoDao() {
		// TODO Auto-generated constructor stub
	}

	public CargoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Cargo cargo = (Cargo) obj;
		cargo.setAtivo(false);
		getEntityManager().merge(cargo);
	}
}
