package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Categoria;
import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.negocio.biblioteca.CategoriaDao;
import br.com.cdan.negocio.biblioteca.TipoDeServicoDao;

public class CategoriaFabricaTest {
	private static CategoriaFabricaTest instance = null;

	public static synchronized CategoriaFabricaTest getInstance() {
		if (instance == null) {
			instance = new CategoriaFabricaTest();
		}
		return instance;
	}

	public Categoria criaCategoria() {
		Categoria c = new Categoria();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao("descricao");
		// Tipos de serviço
		Set<TipoDeServico> tiposDeServico = new LinkedHashSet<>();
		tiposDeServico.add(TipoDeServicoFabricaTest.getInstance().criaTipoDeServico());
		tiposDeServico.add(TipoDeServicoFabricaTest.getInstance().criaTipoDeServico());
		c.setTiposDeServico(tiposDeServico);
		//
		return c;
	}

	public Categoria criaCategoriaPersistido(EntityManager em) {
		Categoria c = criaCategoria();
		CategoriaDao categoriaDao = new CategoriaDao();
		categoriaDao.setEntityManager(em);
		//
		Set<TipoDeServico> tiposDeServico = new LinkedHashSet<>();
		TipoDeServicoDao tipoDeServicoDao = new TipoDeServicoDao();
		tipoDeServicoDao.setEntityManager(em);
		c.getTiposDeServico().forEach(tipoDeServico -> {
			tipoDeServicoDao.persist(tipoDeServico);
			tiposDeServico.add(tipoDeServico);
		});
		c.setTiposDeServico(tiposDeServico);
		//
		return c;
	}
}
