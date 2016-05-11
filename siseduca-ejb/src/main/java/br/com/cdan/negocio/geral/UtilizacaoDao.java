package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeServico;

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
		TipoDeServico tipoDeServico = (TipoDeServico) obj;
		tipoDeServico.setAtivo(false);
		getEntityManager().merge(tipoDeServico);
	}
}
