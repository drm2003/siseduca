package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.ProcessoSeletivo;

public class ProcessoSeletivoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ProcessoSeletivoDao(EntityManager em) {
		setEntityManager(em);
	}

	public ProcessoSeletivoDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ProcessoSeletivo processoSeletivo = (ProcessoSeletivo) obj;
		processoSeletivo.setAtivo(false);
		getEntityManager().merge(processoSeletivo);
	}
}
