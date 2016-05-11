package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Funcionario;

public class FuncionarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public FuncionarioDao(EntityManager em) {
		setEntityManager(em);
	}

	public FuncionarioDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Funcionario funcionario = (Funcionario) obj;
		funcionario.setAtivo(false);
		getEntityManager().merge(funcionario);
	}
}
