package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.financeiro.TipoDeCobrancaRecebimento;

public class TipoDeCobrancaRecebimentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeCobrancaRecebimento tipoDeObra = (TipoDeCobrancaRecebimento) obj;
		tipoDeObra.setAtivo(false);
		getEntityManager().merge(tipoDeObra);
	}
}
