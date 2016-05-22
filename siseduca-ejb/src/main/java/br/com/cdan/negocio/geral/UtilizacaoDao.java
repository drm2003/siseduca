package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Utilizacao;

public class UtilizacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public UtilizacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Utilizacao utilizacao = (Utilizacao) obj;
		utilizacao.setAtivo(false);
		getEntityManager().merge(utilizacao);
	}
}
