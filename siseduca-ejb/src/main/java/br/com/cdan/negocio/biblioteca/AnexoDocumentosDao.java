package br.com.cdan.negocio.biblioteca;

import javax.persistence.EntityManager;

import br.com.cdan.dao.SiseducaDao;
import br.com.cdan.model.pessoa.AnexoDocumentos;

public class AnexoDocumentosDao extends SiseducaDao {
	private static final long serialVersionUID = 1L;

	public AnexoDocumentosDao(EntityManager em) {
		setEntityManager(em);
	}

	public AnexoDocumentosDao() {
	}

	/**
	 * 
	 */
	@Override
	public void remove(Object obj) {
		AnexoDocumentos anexoDocumentos = (AnexoDocumentos) obj;
		anexoDocumentos.setAtivo(false);
		getEntityManager().merge(anexoDocumentos);
	}
}
