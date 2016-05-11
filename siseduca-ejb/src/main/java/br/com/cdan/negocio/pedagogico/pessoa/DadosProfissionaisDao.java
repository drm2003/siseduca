package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.DadosProfissionais;

public class DadosProfissionaisDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DadosProfissionaisDao() {
		// TODO Auto-generated constructor stub
	}

	public DadosProfissionaisDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DadosProfissionais dadosProfissionais = (DadosProfissionais) obj;
		dadosProfissionais.setAtivo(false);
		getEntityManager().merge(dadosProfissionais);
	}
}
