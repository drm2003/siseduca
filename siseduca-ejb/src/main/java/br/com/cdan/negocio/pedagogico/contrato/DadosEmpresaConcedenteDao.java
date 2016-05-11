package br.com.cdan.negocio.pedagogico.contrato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.contrato.DadosEmpresaConcedente;

public class DadosEmpresaConcedenteDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DadosEmpresaConcedenteDao() {
		// TODO Auto-generated constructor stub
	}

	public DadosEmpresaConcedenteDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DadosEmpresaConcedente dadosEmpresaConcedente = (DadosEmpresaConcedente) obj;
		dadosEmpresaConcedente.setAtivo(false);
		getEntityManager().merge(dadosEmpresaConcedente);
	}
}
