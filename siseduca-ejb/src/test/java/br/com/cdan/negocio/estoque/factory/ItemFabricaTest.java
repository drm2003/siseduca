package br.com.cdan.negocio.estoque.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.estoque.Item;
import br.com.cdan.model.estoque.Item_Empresa;

public class ItemFabricaTest {
	private static ItemFabricaTest instance = null;

	public static synchronized ItemFabricaTest getInstance() {
		if (instance == null) {
			instance = new ItemFabricaTest();
		}
		return instance;
	}

	public Item criaItem() {
		Item i = new Item();
		i.setAtivo(Boolean.TRUE);
		i.setClassificacaoDeItens(ClassificacaoDeItensFabricaTest.getInstance().criaClassificacaoDeItens());
		i.setCodigoBarra("codigoBarra");
		i.setDescricao("descricao");
		i.setFinalidade(FinalidadeFabricaTest.getInstance().criaFinalidade());
		//
		Set<Item_Empresa> itens_empresa = new LinkedHashSet<>();
		itens_empresa.add(Item_EmpresaFabricaTest.getInstance().criaItem_Empresa());
		itens_empresa.add(Item_EmpresaFabricaTest.getInstance().criaItem_Empresa());
		i.setItens_Empresa(itens_empresa);
		//
		i.setNcm(NCMFabricaTest.getInstance().criaNCM());
		i.setObservacoes("observacoes");
		i.setUnidadeDeMedida(UnidadeDeMedidaFabricaTest.getInstance().criaUnidadeDeMedida());
		return i;
	}

}
