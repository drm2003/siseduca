package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Aluno;

public class DadoBancarioDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Aluno aluno = (Aluno) obj;
		aluno.setAtivo(false);
		getEntityManager().merge(aluno);
	}
}
