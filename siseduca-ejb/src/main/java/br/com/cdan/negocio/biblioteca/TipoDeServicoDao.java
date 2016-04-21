package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeServico;

public class TipoDeServicoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
