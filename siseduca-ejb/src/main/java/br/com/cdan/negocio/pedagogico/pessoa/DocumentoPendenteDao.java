package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.DocumentoPendente;

public class DocumentoPendenteDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DocumentoPendenteDao() {
		// TODO Auto-generated constructor stub
	}

	public DocumentoPendenteDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DocumentoPendente d = (DocumentoPendente) obj;
		d.setAtivo(false);
		getEntityManager().merge(d);
	}
}
