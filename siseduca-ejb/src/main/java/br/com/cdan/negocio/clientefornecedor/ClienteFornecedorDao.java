package br.com.cdan.negocio.clientefornecedor;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.clientefornecedor.ClienteFornecedor;

public class ClienteFornecedorDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ClienteFornecedorDao() {
		// TODO Auto-generated constructor stub
	}

	public ClienteFornecedorDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ClienteFornecedor c = (ClienteFornecedor) obj;
		c.setAtivo(Boolean.FALSE);
		getEntityManager().merge(c);
	}
}
