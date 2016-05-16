package br.com.cdan.negocio.empresa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.empresa.Empresa;

public class EmpresaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EmpresaDao() {
		// TODO Auto-generated constructor stub
	}

	public EmpresaDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Empresa empresa = (Empresa) obj;
		empresa.setAtivo(Boolean.FALSE);
		getEntityManager().merge(empresa);
	}
}
