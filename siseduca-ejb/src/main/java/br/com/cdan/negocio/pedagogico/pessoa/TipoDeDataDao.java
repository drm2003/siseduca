package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.TipoDeData;

public class TipoDeDataDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeDataDao() {
		// TODO Auto-generated constructor stub
	}

	public TipoDeDataDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeData tipoDeData = (TipoDeData) obj;
		tipoDeData.setAtivo(false);
		getEntityManager().merge(tipoDeData);
	}
}
