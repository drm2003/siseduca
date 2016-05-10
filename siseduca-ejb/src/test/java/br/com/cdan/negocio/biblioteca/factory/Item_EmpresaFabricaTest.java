package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.estoque.Item_Empresa;
import br.com.cdan.negocio.biblioteca.EmpresaDao;
import br.com.cdan.negocio.biblioteca.ItemDao;
import br.com.cdan.negocio.biblioteca.Item_EmpresaDao;

public class Item_EmpresaFabricaTest {
	private static Item_EmpresaFabricaTest instance = null;

	public static synchronized Item_EmpresaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Item_EmpresaFabricaTest();
		}
		return instance;
	}

	public Item_Empresa criaItem_Empresa() {
		Item_Empresa i = new Item_Empresa();
		i.setAtivo(Boolean.TRUE);
		i.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		i.setEstMaximo(BigDecimal.TEN);
		i.setEstMinimo(BigDecimal.ZERO);
		i.setItem(ItemFabricaTest.getInstance().criaItem());
		i.setUtiliza(Boolean.TRUE);
		i.setValorCusto(BigDecimal.TEN);
		i.setValorVenda(BigDecimal.TEN);
		return i;
	}

	public Item_Empresa criaItem_EmpresaPersistido(EntityManager em) {
		Item_Empresa i = criaItem_Empresa();
		Item_EmpresaDao dao = new Item_EmpresaDao(em);
		//
		EmpresaDao empresaDao = new EmpresaDao(em);
		Empresa empresa = i.getEmpresa();
		empresaDao.persist(empresa);
		i.setEmpresa(empresa);
		//
		ItemDao itemDao = new ItemDao(em);
		Item item = i.getItem();
		itemDao.persist(item);
		i.setItem(item);
		//
		dao.persist(i);
		return i;
	}

}
