package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.SerieColecaoLiteral;

public class SerieColecaoLiteralDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		SerieColecaoLiteral serieColecaoLiteral = (SerieColecaoLiteral) obj;
		serieColecaoLiteral.setAtivo(false);
		getEntityManager().merge(serieColecaoLiteral);
	}
}
