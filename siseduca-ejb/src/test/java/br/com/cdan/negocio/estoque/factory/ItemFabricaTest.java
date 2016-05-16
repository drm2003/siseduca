package br.com.cdan.negocio.estoque.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.estoque.Item;
import br.com.cdan.negocio.estoque.ItemDao;

public class ItemFabricaTest {
	private static ItemFabricaTest instance = null;

	public static synchronized ItemFabricaTest getInstance() {
		if (instance == null) {
			instance = new ItemFabricaTest();
		}
		return instance;
	}

	public Item criaItem(EntityManager em) {
		Item i = new Item();
		i.setAtivo(Boolean.TRUE);
		i.setClassificacaoDeItens(ClassificacaoDeItensFabricaTest.getInstance().criaClassificacaoDeItensPersistido(em));
		i.setCodigoBarra("codigoBarra");
		i.setDescricao("descricao");
		i.setFinalidade(FinalidadeFabricaTest.getInstance().criaFinalidadePersistido(em));
		i.setNcm(NCMFabricaTest.getInstance().criaNCMPersistido(em));
		i.setObservacoes("observacoes");
		i.setUnidadeDeMedida(UnidadeDeMedidaFabricaTest.getInstance().criaUnidadeDeMedidaPersistido(em));
		return i;
	}

	public Item criaItemPersistido(EntityManager em) {
		Item i = criaItem(em);
		ItemDao dao = new ItemDao(em);
		dao.persist(i);
		return i;
	}

}
