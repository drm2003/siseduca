package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Classificacao;

public class ClassificacaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ClassificacaoDao() {
		// TODO Auto-generated constructor stub
	}

	public ClassificacaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Classificacao classificacao = (Classificacao) obj;
		classificacao.setAtivo(false);
		getEntityManager().merge(classificacao);
	}
}
