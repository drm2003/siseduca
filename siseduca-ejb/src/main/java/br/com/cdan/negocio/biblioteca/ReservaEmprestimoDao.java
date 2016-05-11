package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.ReservaEmprestimo;

public class ReservaEmprestimoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ReservaEmprestimoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ReservaEmprestimo r = (ReservaEmprestimo) obj;
		r.setAtivo(false);
		getEntityManager().merge(r);
	}
}
