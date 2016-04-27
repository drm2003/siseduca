package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;

public class OcorrenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public OcorrenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	public OcorrenciaDao() {
	}

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
