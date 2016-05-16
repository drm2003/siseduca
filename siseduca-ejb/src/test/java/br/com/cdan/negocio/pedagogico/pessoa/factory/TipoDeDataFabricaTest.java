package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.TipoDeData;
import br.com.cdan.negocio.pedagogico.pessoa.TipoDeDataDao;

public class TipoDeDataFabricaTest {
	private static TipoDeDataFabricaTest instance = null;

	public static synchronized TipoDeDataFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeDataFabricaTest();
		}
		return instance;
	}

	public TipoDeData criaTipoDeData(EntityManager em) {
		TipoDeData t = new TipoDeData();
		t.setAtivo(Boolean.TRUE);
		t.setCor("cor");
		t.setDescricao("descricao");
		t.setFeriadoEvento(FeriadoEventoFabricaTest.getInstance().criaFeriadoEventoPersistido(em));
		t.setTemAula(Boolean.TRUE);
		return t;
	}

	public TipoDeData criaTipoDeDataPersistido(EntityManager em) {
		TipoDeData t = criaTipoDeData(em);
		TipoDeDataDao dao = new TipoDeDataDao(em);
		dao.persist(t);
		return t;
	}
}
