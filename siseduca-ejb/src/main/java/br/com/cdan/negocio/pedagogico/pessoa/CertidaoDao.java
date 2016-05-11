package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Certidao;

public class CertidaoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public CertidaoDao() {
		// TODO Auto-generated constructor stub
	}

	public CertidaoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Certidao certidao = (Certidao) obj;
		certidao.setAtivo(false);
		getEntityManager().merge(certidao);
	}
}
