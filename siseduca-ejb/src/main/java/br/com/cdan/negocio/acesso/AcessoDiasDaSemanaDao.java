package br.com.cdan.negocio.acesso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.acesso.AcessoDiasDaSemana;

public class AcessoDiasDaSemanaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AcessoDiasDaSemanaDao(EntityManager em) {
		setEntityManager(em);
	}

	public AcessoDiasDaSemanaDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		AcessoDiasDaSemana acessoDiasDaSemana = (AcessoDiasDaSemana) obj;
		acessoDiasDaSemana.setAtivo(Boolean.FALSE);
		getEntityManager().merge(acessoDiasDaSemana);
	}
}
