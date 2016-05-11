package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeDocumentoDoAluno;

public class TipoDeDocumentoDoAlunoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeDocumentoDoAlunoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeDocumentoDoAluno t = (TipoDeDocumentoDoAluno) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
