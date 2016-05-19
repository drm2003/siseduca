package br.com.cdan.negocio.estoque.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Item_Empresa;
import br.com.cdan.model.estoque.Item_EmpresaPK;
import br.com.cdan.negocio.empresa.factory.EmpresaFabricaTest;
import br.com.cdan.negocio.estoque.Item_EmpresaDao;

public class Item_EmpresaFabricaTest {
	private static Item_EmpresaFabricaTest instance = null;

	public static synchronized Item_EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Item_EmpresaFabricaTest();
		}
		return instance;
	}

	public Item_Empresa criaItem_Empresa(EntityManager em) {
		Item_Empresa i = new Item_Empresa();
		i.setAtivo(Boolean.TRUE);
		i.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresaPersistido(em));
		i.setEstMaximo(BigDecimal.TEN);
		i.setEstMinimo(BigDecimal.ZERO);
		i.setItem(ItemFabricaTest.getInstance().criaItemPersistido(em));
		i.setUtiliza(Boolean.TRUE);
		i.setValorCusto(BigDecimal.TEN);
		i.setValorVenda(BigDecimal.TEN);
		i.setId(new Item_EmpresaPK(i.getItem().getId(), i.getEmpresa().getId()));
		return i;
	}

	public Item_Empresa criaItem_EmpresaPersistido(EntityManager em) {
		Item_Empresa i = criaItem_Empresa(em);
		Item_EmpresaDao dao = new Item_EmpresaDao(em);
		//
		dao.persist(i);
		return i;
	}

}
