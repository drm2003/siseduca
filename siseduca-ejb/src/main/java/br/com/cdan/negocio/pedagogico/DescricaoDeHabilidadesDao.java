package br.com.cdan.negocio.pedagogico;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;

public class DescricaoDeHabilidadesDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DescricaoDeHabilidadesDao() {
		// TODO Auto-generated constructor stub
	}

	public DescricaoDeHabilidadesDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		DescricaoDeHabilidades d = (DescricaoDeHabilidades) obj;
		d.setAtivo(false);
		getEntityManager().merge(d);
	}
}
