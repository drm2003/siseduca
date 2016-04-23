package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.HorarioDeAcesso;

public class HorarioDeAcessoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
