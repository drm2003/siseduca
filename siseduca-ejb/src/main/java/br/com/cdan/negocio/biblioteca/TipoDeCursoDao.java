package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.TipoDeCurso;

public class TipoDeCursoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeCurso tipoDeCurso = (TipoDeCurso) obj;
		tipoDeCurso.setAtivo(false);
		getEntityManager().merge(tipoDeCurso);
	}
}
