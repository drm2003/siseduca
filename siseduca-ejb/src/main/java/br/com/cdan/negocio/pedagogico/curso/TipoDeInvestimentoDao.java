package br.com.cdan.negocio.pedagogico.curso;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.TipoDeInvestimento;

public class TipoDeInvestimentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeInvestimentoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeInvestimento tipoDeInvestimento = (TipoDeInvestimento) obj;
		tipoDeInvestimento.setAtivo(false);
		getEntityManager().merge(tipoDeInvestimento);
	}
}
