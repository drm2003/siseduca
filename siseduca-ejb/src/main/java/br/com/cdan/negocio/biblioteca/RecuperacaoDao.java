package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;

public class RecuperacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public RecuperacaoDao() {
		// TODO Auto-generated constructor stub
	}

	public RecuperacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		RequisitoParaOCurso requisito = (RequisitoParaOCurso) obj;
		requisito.setAtivo(false);
		getEntityManager().merge(requisito);
	}
}
