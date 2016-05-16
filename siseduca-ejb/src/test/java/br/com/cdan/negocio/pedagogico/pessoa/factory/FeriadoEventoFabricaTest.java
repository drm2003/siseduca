package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.FeriadoEvento;
import br.com.cdan.negocio.pedagogico.factory.CalendarioLetivoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.FeriadoEventoDao;

public class FeriadoEventoFabricaTest {
	private static FeriadoEventoFabricaTest instance = null;

	public static synchronized FeriadoEventoFabricaTest getInstance() {
		if (instance == null) {
			instance = new FeriadoEventoFabricaTest();
		}
		return instance;
	}

	public FeriadoEvento criaFeriadoEvento(EntityManager em) {
		FeriadoEvento f = new FeriadoEvento();
		f.setAtivo(Boolean.TRUE);
		f.setCalendarioLetivo(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivoPersistido(em));
		f.setDataFinal(Calendar.getInstance());
		f.setDataInicio(Calendar.getInstance());
		f.setDescricao("descricao");
		f.setTipoDeData(TipoDeDataFabricaTest.getInstance().criaTipoDeDataPersistido(em));
		return f;
	}

	public FeriadoEvento criaFeriadoEventoPersistido(EntityManager em) {
		FeriadoEvento f = criaFeriadoEvento(em);
		FeriadoEventoDao dao = new FeriadoEventoDao(em);
		//
		dao.persist(f);
		return f;
	}

}
