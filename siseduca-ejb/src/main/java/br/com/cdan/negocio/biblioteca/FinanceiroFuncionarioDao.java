package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.FinanceiroFuncionario;

public class FinanceiroFuncionarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public FinanceiroFuncionarioDao(EntityManager em) {
		setEntityManager(em);
	}

	public FinanceiroFuncionarioDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		FinanceiroFuncionario financeiroFuncionario = (FinanceiroFuncionario) obj;
		financeiroFuncionario.setAtivo(false);
		getEntityManager().merge(financeiroFuncionario);
	}
}
