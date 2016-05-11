package br.com.cdan.negocio.pedagogico.diario;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;

public class ControleDeFrequenciaDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public ControleDeFrequenciaDao(EntityManager em) {
		setEntityManager(em);
	}

	public ControleDeFrequenciaDao() {

	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		ControleDeFrequencia controleDeFrequencia = (ControleDeFrequencia) obj;
		controleDeFrequencia.setAtivo(false);
		getEntityManager().merge(controleDeFrequencia);
	}
}
