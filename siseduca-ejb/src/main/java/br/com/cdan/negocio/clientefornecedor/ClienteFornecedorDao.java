package br.com.cdan.negocio.clientefornecedor;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.estoque.ClassificacaoDeItens;

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
		ClassificacaoDeItens classificacaoDeItens = (ClassificacaoDeItens) obj;
		classificacaoDeItens.setAtivo(false);
		getEntityManager().merge(classificacaoDeItens);
	}
}
