package br.com.cdan.negocio.acesso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.HorarioDeAcesso;

public class HorarioDeAcessoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public HorarioDeAcessoDao() {
		// TODO Auto-generated constructor stub
	}

	public HorarioDeAcessoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		HorarioDeAcesso horarioDeAcesso = (HorarioDeAcesso) obj;
		horarioDeAcesso.setAtivo(false);
		getEntityManager().merge(horarioDeAcesso);
	}
}
