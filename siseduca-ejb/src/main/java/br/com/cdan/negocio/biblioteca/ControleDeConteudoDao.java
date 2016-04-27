package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.diario.ControleDeConteudo;

public class ControleDeConteudoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ControleDeConteudoDao(EntityManager em) {
		setEntityManager(em);
	}

	public ControleDeConteudoDao() {

	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ControleDeConteudo controleDeConteudo = (ControleDeConteudo) obj;
		controleDeConteudo.setAtivo(false);
		getEntityManager().merge(controleDeConteudo);
	}
}
