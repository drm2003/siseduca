package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Cidade;

public class CidadeDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CidadeDao() {
		// TODO Auto-generated constructor stub
	}

	public CidadeDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Cidade cidade = (Cidade) obj;
		cidade.setAtivo(false);
		getEntityManager().merge(cidade);
	}
}
