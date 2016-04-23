package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Funcionario;

public class FuncionarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

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
