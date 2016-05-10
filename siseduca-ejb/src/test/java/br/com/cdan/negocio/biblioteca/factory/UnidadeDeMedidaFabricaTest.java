package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.estoque.UnidadeDeMedida;

public class UnidadeDeMedidaFabricaTest {
	private static UnidadeDeMedidaFabricaTest instance = null;

	public static synchronized UnidadeDeMedidaFabricaTest getInstance() {
		if (instance == null) {
			instance = new UnidadeDeMedidaFabricaTest();
		}
		return instance;
	}

	public UnidadeDeMedida criaUnidadeDeMedida() {
		UnidadeDeMedida u = new UnidadeDeMedida();
		u.setAtivo(Boolean.TRUE);
		u.setDescricao("descricao");
		//
		Set<Item> itens = new LinkedHashSet<>();
		itens.add(ItemFabricaTest.getInstance().criaItem());
		itens.add(ItemFabricaTest.getInstance().criaItem());
		u.setItens(itens);
		//
		return u;
	}

	public UnidadeDeMedida criaUnidadeDeMedidaPersistido(EntityManager em) {
		UnidadeDeMedida u = new UnidadeDeMedida();
		u.setAtivo(Boolean.TRUE);
		u.setDescricao("descricao");
		//
		Set<Item> itens = new LinkedHashSet<>();
		itens.add(ItemFabricaTest.getInstance().criaItem());
		itens.add(ItemFabricaTest.getInstance().criaItem());
		u.setItens(itens);
		//
		return u;
	}
}
