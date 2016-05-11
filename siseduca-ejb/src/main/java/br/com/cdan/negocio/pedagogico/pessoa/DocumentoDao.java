package br.com.cdan.negocio.pedagogico.pessoa;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.Documento;

public class DocumentoDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public DocumentoDao() {
		// TODO Auto-generated constructor stub
	}

	public DocumentoDao(EntityManager em) {
		setEntityManager(em);
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		Documento documento = (Documento) obj;
		documento.setAtivo(false);
		getEntityManager().merge(documento);
	}
}
