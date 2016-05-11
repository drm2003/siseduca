package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Categoria;

public class CategoriaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CategoriaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Categoria categoria = (Categoria) obj;
		categoria.setAtivo(false);
		getEntityManager().merge(categoria);
	}
}
