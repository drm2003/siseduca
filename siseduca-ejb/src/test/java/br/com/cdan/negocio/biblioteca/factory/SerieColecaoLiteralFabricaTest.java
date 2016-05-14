package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.SerieColecaoLiteral;
import br.com.cdan.negocio.biblioteca.SerieColecaoLiteralDao;

public class SerieColecaoLiteralFabricaTest {
	private static SerieColecaoLiteralFabricaTest instance;

	public static synchronized SerieColecaoLiteralFabricaTest getInstance() {
		if (instance == null) {
			instance = new SerieColecaoLiteralFabricaTest();
		}
		return instance;
	}

	public SerieColecaoLiteral criaSerieColecaoLiteral() {
		SerieColecaoLiteral s = new SerieColecaoLiteral();
		s.setAtivo(Boolean.TRUE);
		s.setCompartilhado(Boolean.TRUE);
		s.setDescricao("descricao" + Math.random() * 10000);
		//
		return s;
	}

	public SerieColecaoLiteral criaSerieColecaoLiteralPersistido(EntityManager em) {
		SerieColecaoLiteral s = criaSerieColecaoLiteral();
		SerieColecaoLiteralDao dao = new SerieColecaoLiteralDao(em);
		//
		dao.persist(s);
		return s;
	}

}
