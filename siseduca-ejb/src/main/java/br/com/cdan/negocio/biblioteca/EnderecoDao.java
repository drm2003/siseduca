package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Endereco;

public class EnderecoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EnderecoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Endereco endereco = (Endereco) obj;
		endereco.setAtivo(false);
		getEntityManager().merge(endereco);
	}
}
