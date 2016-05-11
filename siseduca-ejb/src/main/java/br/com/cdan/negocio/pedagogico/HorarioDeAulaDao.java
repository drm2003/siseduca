package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.HorarioDeAula;

public class HorarioDeAulaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public HorarioDeAulaDao() {
		// TODO Auto-generated constructor stub
	}

	public HorarioDeAulaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		HorarioDeAula horarioDeAula = (HorarioDeAula) obj;
		horarioDeAula.setAtivo(false);
		getEntityManager().merge(horarioDeAula);
	}
}
