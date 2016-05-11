package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contrato.MotivoDeNaoFechamentoDeContrato;

public class MotivoDeNaoFechamentoDeContratoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MotivoDeNaoFechamentoDeContratoDao() {
	}

	public MotivoDeNaoFechamentoDeContratoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MotivoDeNaoFechamentoDeContrato m = (MotivoDeNaoFechamentoDeContrato) obj;
		m.setAtivo(false);
		getEntityManager().merge(m);
	}
}
