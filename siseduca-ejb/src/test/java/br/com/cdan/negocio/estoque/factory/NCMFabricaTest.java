package br.com.cdan.negocio.estoque.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.estoque.NCM;
import br.com.cdan.negocio.estoque.ItemDao;
import br.com.cdan.negocio.estoque.NCMDao;

public class NCMFabricaTest {
	private static NCMFabricaTest instance = null;

	public static synchronized NCMFabricaTest getInstance() {
		if (instance == null) {
			instance = new NCMFabricaTest();
		}
		return instance;
	}

	public NCM criaNCM() {
		NCM ncm = new NCM();
		ncm.setAtivo(Boolean.TRUE);
		ncm.setDescricao("descricao");
		//
		Set<Item> itens = new LinkedHashSet<>();
		itens.add(ItemFabricaTest.getInstance().criaItem());
		itens.add(ItemFabricaTest.getInstance().criaItem());
		ncm.setItens(itens);
		//
		return ncm;
	}

	public NCM criaNCMPersistido(EntityManager em) {
		NCM ncm = criaNCM();
		NCMDao dao = new NCMDao(em);
		//
		ItemDao itemDao = new ItemDao(em);
		Set<Item> itens = new LinkedHashSet<>();
		ncm.getItens().forEach(item -> {
			itemDao.persist(item);
			itens.add(item);
		});
		ncm.setItens(itens);
		//
		dao.persist(ncm);
		return ncm;
	}

}
