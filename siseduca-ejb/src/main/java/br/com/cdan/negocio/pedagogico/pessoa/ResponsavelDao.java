package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Responsavel;

public class ResponsavelDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ResponsavelDao() {
		// TODO Auto-generated constructor stub
	}

	public ResponsavelDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Responsavel responsavel = (Responsavel) obj;
		responsavel.setAtivo(false);
		getEntityManager().merge(responsavel);
	}
}
