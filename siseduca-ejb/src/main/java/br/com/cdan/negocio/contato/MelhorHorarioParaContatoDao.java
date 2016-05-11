package br.com.cdan.negocio.contato;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.contato.MelhorHorarioParaContato;

public class MelhorHorarioParaContatoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public MelhorHorarioParaContatoDao() {
	}

	public MelhorHorarioParaContatoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		MelhorHorarioParaContato melhorHorarioParaContato = (MelhorHorarioParaContato) obj;
		melhorHorarioParaContato.setAtivo(false);
		getEntityManager().merge(melhorHorarioParaContato);
	}
}
