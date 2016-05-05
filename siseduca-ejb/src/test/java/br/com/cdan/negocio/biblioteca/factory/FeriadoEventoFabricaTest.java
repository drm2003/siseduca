package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.model.pessoa.FeriadoEvento;
import br.com.cdan.model.pessoa.TipoDeData;
import br.com.cdan.negocio.biblioteca.CalendarioLetivoDao;
import br.com.cdan.negocio.biblioteca.FeriadoEventoDao;
import br.com.cdan.negocio.biblioteca.TipoDeDataDao;

public class FeriadoEventoFabricaTest {
	private static FeriadoEventoFabricaTest instance = null;

	public static synchronized FeriadoEventoFabricaTest getInstance() {
		if (instance == null) {
			instance = new FeriadoEventoFabricaTest();
		}
		return instance;
	}

	public FeriadoEvento criaFeriadoEvento() {
		FeriadoEvento f = new FeriadoEvento();
		f.setAtivo(Boolean.TRUE);
		f.setCalendarioLetivo(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		f.setDataFinal(Calendar.getInstance());
		f.setDataInicio(Calendar.getInstance());
		f.setDescricao("descricao");
		f.setTipoDeData(TipoDeDataFabricaTest.getInstance().criaTipoDeData());
		return f;
	}

	public FeriadoEvento criaFeriadoEventoPersistido(EntityManager em) {
		FeriadoEvento f = criaFeriadoEvento();
		FeriadoEventoDao dao = new FeriadoEventoDao(em);
		//
		CalendarioLetivo calendarioLetivo = f.getCalendarioLetivo();
		CalendarioLetivoDao calendarioLetivoDao = new CalendarioLetivoDao(em);
		calendarioLetivoDao.persist(calendarioLetivo);
		f.setCalendarioLetivo(calendarioLetivo);
		//
		TipoDeDataDao tipoDeDataDao = new TipoDeDataDao(em);
		TipoDeData tipoDeData = f.getTipoDeData();
		tipoDeDataDao.persist(tipoDeData);
		f.setTipoDeData(tipoDeData);
		//
		dao.persist(f);
		return f;
	}

}
