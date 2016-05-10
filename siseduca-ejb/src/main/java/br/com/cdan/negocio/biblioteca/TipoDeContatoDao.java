package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contato.TipoDeContato;

public class TipoDeContatoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeContatoDao() {
	}

	public TipoDeContatoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeContato tipoDeContato = (TipoDeContato) obj;
		tipoDeContato.setAtivo(false);
		getEntityManager().merge(tipoDeContato);
	}
}
