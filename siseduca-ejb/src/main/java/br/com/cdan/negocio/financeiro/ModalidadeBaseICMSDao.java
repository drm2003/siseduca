package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ModalidadeBaseICMS;

public class ModalidadeBaseICMSDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ModalidadeBaseICMSDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ModalidadeBaseICMS m = (ModalidadeBaseICMS) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
