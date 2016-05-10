package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.TipoDeObra;

public class TipoDeObraDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeObraDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeObra tipoDeObra = (TipoDeObra) obj;
		tipoDeObra.setAtivo(false);
		getEntityManager().merge(tipoDeObra);
	}
}
