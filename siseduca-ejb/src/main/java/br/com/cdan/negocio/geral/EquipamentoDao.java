package br.com.cdan.negocio.geral;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.geral.Equipamento;

public class EquipamentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public EquipamentoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Equipamento equipamento = (Equipamento) obj;
		equipamento.setAtivo(false);
		getEntityManager().merge(equipamento);
	}
}
