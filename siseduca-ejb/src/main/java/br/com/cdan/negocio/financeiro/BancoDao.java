package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.Banco;

public class BancoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public BancoDao() {
		// TODO Auto-generated constructor stub
	}

	public BancoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Banco banco = (Banco) obj;
		banco.setAtivo(false);
		getEntityManager().merge(banco);
	}
}
