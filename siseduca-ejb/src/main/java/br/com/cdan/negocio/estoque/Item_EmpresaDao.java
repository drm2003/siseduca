package br.com.cdan.negocio.estoque;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.Item_Empresa;

public class Item_EmpresaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public Item_EmpresaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Item_Empresa item_Empresa = (Item_Empresa) obj;
		item_Empresa.setAtivo(false);
		getEntityManager().merge(item_Empresa);
	}
}
