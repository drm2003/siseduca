package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.CarteiraHabilitacao;

public class CarteiraHabilitacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CarteiraHabilitacaoDao() {
		// TODO Auto-generated constructor stub
	}

	public CarteiraHabilitacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		CarteiraHabilitacao carteiraHabilitacao = (CarteiraHabilitacao) obj;
		carteiraHabilitacao.setAtivo(false);
		getEntityManager().merge(carteiraHabilitacao);
	}
}
