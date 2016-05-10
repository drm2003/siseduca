package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.Escolaridade;

public class EscolaridadeDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EscolaridadeDao(EntityManager em) {
		setEntityManager(em);
	}

	public EscolaridadeDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Escolaridade escolaridade = (Escolaridade) obj;
		escolaridade.setAtivo(false);
		getEntityManager().merge(escolaridade);
	}
}
