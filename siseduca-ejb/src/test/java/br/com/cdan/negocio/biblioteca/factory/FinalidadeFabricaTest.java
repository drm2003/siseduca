package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Finalidade;
import br.com.cdan.model.estoque.Item;
import br.com.cdan.negocio.biblioteca.FinalidadeDao;
import br.com.cdan.negocio.biblioteca.ItemDao;

public class FinalidadeFabricaTest {
	private static FinalidadeFabricaTest instance = null;

	public static synchronized FinalidadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new FinalidadeFabricaTest();
		}
		return instance;
	}

	public Finalidade criaFinalidade() {
		Finalidade f = new Finalidade();
		f.setAtivo(Boolean.TRUE);
		f.setDescricao("descricao");
		//
		Set<Item> itens = new LinkedHashSet<>();
		itens.add(ItemFabricaTest.getInstance().criaItem());
		itens.add(ItemFabricaTest.getInstance().criaItem());
		f.setItens(itens);
		//
		return f;
	}

	public Finalidade criaFinalidadePersistido(EntityManager em) {
		Finalidade f = criaFinalidade();
		FinalidadeDao dao = new FinalidadeDao(em);
		//
		ItemDao itemDao = new ItemDao(em);
		Set<Item> itens = new LinkedHashSet<>();
		f.getItens().forEach(item -> {
			itemDao.persist(item);
			itens.add(item);
		});
		f.setItens(itens);
		//
		dao.persist(f);
		return f;
	}

}
