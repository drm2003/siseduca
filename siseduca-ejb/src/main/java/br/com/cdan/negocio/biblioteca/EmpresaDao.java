package br.com.cdan.negocio.biblioteca;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.empresa.Empresa;

public class EmpresaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Empresa empresa = (Empresa) obj;
		empresa.setAtivo(false);
		getEntityManager().merge(empresa);
	}
}
