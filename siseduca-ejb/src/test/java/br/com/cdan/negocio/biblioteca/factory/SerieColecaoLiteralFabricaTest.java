package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.model.biblioteca.SerieColecaoLiteral;
import br.com.cdan.negocio.biblioteca.ObraDao;
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
		s.setDescricao("descricao");
		//
		Set<Obra> obras = new LinkedHashSet<>();
		obras.add(ObraFabricaTest.getInstance().criaObra());
		obras.add(ObraFabricaTest.getInstance().criaObra());
		s.setObras(obras);
		//
		return s;
	}

	public SerieColecaoLiteral criaSerieColecaoLiteralPersistido(EntityManager em) {
		SerieColecaoLiteral s = criaSerieColecaoLiteral();
		SerieColecaoLiteralDao dao = new SerieColecaoLiteralDao(em);
		//
		Set<Obra> obras = new LinkedHashSet<>();
		ObraDao obraDao = new ObraDao(em);
		s.getObras().forEach(obra -> {
			obraDao.persist(obra);
			obras.add(obra);
		});
		s.setObras(obras);
		//
		dao.persist(s);
		return s;
	}

}
