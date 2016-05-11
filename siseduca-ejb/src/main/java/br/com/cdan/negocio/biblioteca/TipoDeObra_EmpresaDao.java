package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.TipoDeObra_Empresa;

public class TipoDeObra_EmpresaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public TipoDeObra_EmpresaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		TipoDeObra_Empresa t = (TipoDeObra_Empresa) obj;
		t.setAtivo(false);
		getEntityManager().merge(t);
	}
}
