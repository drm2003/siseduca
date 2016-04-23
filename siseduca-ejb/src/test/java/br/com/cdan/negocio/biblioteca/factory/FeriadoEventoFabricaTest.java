package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import br.com.cdan.model.pessoa.FeriadoEvento;

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

}
