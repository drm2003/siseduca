package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;

public class RequisitoParaOCursoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public RequisitoParaOCursoDao() {
		// TODO Auto-generated constructor stub
	}

	public RequisitoParaOCursoDao(EntityManager em) {
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
