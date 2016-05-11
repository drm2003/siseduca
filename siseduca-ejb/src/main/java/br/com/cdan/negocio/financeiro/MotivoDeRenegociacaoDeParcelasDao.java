package br.com.cdan.negocio.financeiro;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.MotivoDeRenegociacaoDeParcelas;

public class MotivoDeRenegociacaoDeParcelasDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeRenegociacaoDeParcelasDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MotivoDeRenegociacaoDeParcelas m = (MotivoDeRenegociacaoDeParcelas) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
