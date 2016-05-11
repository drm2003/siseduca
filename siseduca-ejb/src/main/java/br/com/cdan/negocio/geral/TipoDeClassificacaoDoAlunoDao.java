package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.TipoDeClassificacaoDoAluno;

public class TipoDeClassificacaoDoAlunoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeClassificacaoDoAlunoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeClassificacaoDoAluno t = (TipoDeClassificacaoDoAluno) obj;
		t.setAtivo(false);
		getEntityManager().merge(t	);
	}
}
