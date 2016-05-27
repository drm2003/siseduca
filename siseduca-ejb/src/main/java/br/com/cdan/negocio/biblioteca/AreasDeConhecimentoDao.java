package br.com.cdan.negocio.biblioteca;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.biblioteca.AreasDeConhecimento;

@Stateless
public class AreasDeConhecimentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@Override
	public void remove(Object obj) {
		AreasDeConhecimento areas = (AreasDeConhecimento) obj;
		areas.setAtivo(false);
		getEntityManager().merge(areas);
	}

	public List<AreasDeConhecimento> lista() {
		String sql = "FROM AreasDeConhecimento a";
		TypedQuery<AreasDeConhecimento> query = getEntityManager().createQuery(sql, AreasDeConhecimento.class);
		return query.getResultList();
	}
}
