package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;

public class DadosEmpresaConcedenteDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DadosEmpresaConcedenteDao() {
		// TODO Auto-generated constructor stub
	}

	public DadosEmpresaConcedenteDao(EntityManager em) {
		setEntityManager(em);
	}
}
