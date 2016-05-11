package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.TipoDeOcorrencia;

public class TipoDeOcorrenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeOcorrenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeOcorrencia tipoDeOcorrencia = (TipoDeOcorrencia) obj;
		tipoDeOcorrencia.setAtivo(false);
		getEntityManager().merge(tipoDeOcorrencia);
	}
}
