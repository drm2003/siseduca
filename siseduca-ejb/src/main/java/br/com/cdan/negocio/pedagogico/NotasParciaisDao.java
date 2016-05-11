package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.NotasParciais;

public class NotasParciaisDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public NotasParciaisDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		NotasParciais notasParciais = (NotasParciais) obj;
		notasParciais.setAtivo(false);
		getEntityManager().merge(notasParciais);
	}
}
