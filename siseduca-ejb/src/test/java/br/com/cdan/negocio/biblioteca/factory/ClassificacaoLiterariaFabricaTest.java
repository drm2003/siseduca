package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;
import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.negocio.biblioteca.ClassificacaoLiterariaDao;
import br.com.cdan.negocio.biblioteca.ObraDao;

public class ClassificacaoLiterariaFabricaTest {
	private static ClassificacaoLiterariaFabricaTest instance = null;

	public static synchronized ClassificacaoLiterariaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ClassificacaoLiterariaFabricaTest();
		}
		return instance;
	}

	public ClassificacaoLiteraria criaClassificacaoLiteraria() {
		ClassificacaoLiteraria c = new ClassificacaoLiteraria();
		c.setAtivo(Boolean.TRUE);
		c.setCompartilhado(Boolean.TRUE);
		c.setDescricao("descricao");
		//
		Set<Obra> obras = new LinkedHashSet<>();
		obras.add(ObraFabricaTest.getInstance().criaObra());
		obras.add(ObraFabricaTest.getInstance().criaObra());
		c.setObras(obras);
		return c;
	}

	public ClassificacaoLiteraria criaClassificacaoLiterariaPersistido(EntityManager em) {
		ClassificacaoLiteraria c = criaClassificacaoLiteraria();
		ClassificacaoLiterariaDao dao = new ClassificacaoLiterariaDao(em);
		//
		ObraDao obraDao = new ObraDao(em);
		Set<Obra> obras = new LinkedHashSet<>();
		c.getObras().forEach(obra -> {
			obraDao.persist(obra);
			obras.add(obra);
		});
		c.setObras(obras);
		//
		dao.persist(c);
		return c;
	}

}
