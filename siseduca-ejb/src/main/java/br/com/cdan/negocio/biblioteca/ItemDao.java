package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.Item;

public class ItemDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ItemDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Item item = (Item) obj;
		item.setAtivo(false);
		getEntityManager().merge(item);
	}
}
