package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.FeriadoEvento;
import br.com.cdan.model.pessoa.TipoDeData;
import br.com.cdan.negocio.biblioteca.FeriadoEventoDao;
import br.com.cdan.negocio.biblioteca.TipoDeDataDao;

public class TipoDeDataFabricaTest {
	private static TipoDeDataFabricaTest instance = null;

	public static synchronized TipoDeDataFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeDataFabricaTest();
		}
		return instance;
	}

	public TipoDeData criaTipoDeData() {
		TipoDeData t = new TipoDeData();
		t.setAtivo(Boolean.TRUE);
		t.setCor("cor");
		t.setDescricao("descricao");
		t.setFeriadoEvento(FeriadoEventoFabricaTest.getInstance().criaFeriadoEvento());
		t.setTemAula(Boolean.TRUE);
		return t;
	}

	public TipoDeData criaTipoDeDataPersistido(EntityManager em) {
		TipoDeData t = criaTipoDeData();
		TipoDeDataDao dao = new TipoDeDataDao(em);
		//
		FeriadoEvento feriadoEvento = t.getFeriadoEvento();
		FeriadoEventoDao feriadoEventoDao = new FeriadoEventoDao(em);
		feriadoEventoDao.persist(feriadoEvento);
		t.setFeriadoEvento(feriadoEvento);
		//
		dao.persist(feriadoEvento);
		return t;
	}
}
