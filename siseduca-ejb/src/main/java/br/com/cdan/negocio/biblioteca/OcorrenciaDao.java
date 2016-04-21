package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;

public class OcorrenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Ocorrencia ocorrencia = (Ocorrencia) obj;
		ocorrencia.setAtivo(false);
		getEntityManager().merge(ocorrencia);
	}
}
