package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;

public class DiaDaSemanaAulaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DiaDaSemanaAulaDao(EntityManager em) {
		setEntityManager(em);
	}

	public DiaDaSemanaAulaDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DiaDaSemanaAula diaDaSemanaAula = (DiaDaSemanaAula) obj;
		diaDaSemanaAula.setAtivo(false);
		getEntityManager().merge(diaDaSemanaAula);
	}
}
