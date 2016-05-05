package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.curso.Conteudo;

public class ConteudoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ConteudoDao() {
	}

	public ConteudoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Conteudo conteudo = (Conteudo) obj;
		conteudo.setAtivo(false);
		getEntityManager().merge(conteudo);
	}
}
