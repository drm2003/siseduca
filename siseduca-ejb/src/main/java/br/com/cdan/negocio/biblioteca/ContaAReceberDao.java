package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.ContaAReceber;

public class ContaAReceberDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ContaAReceberDao() {
		// TODO Auto-generated constructor stub
	}

	public ContaAReceberDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ContaAReceber contaAReceber = (ContaAReceber) obj;
		contaAReceber.setAtivo(false);
		getEntityManager().merge(contaAReceber);
	}
}
