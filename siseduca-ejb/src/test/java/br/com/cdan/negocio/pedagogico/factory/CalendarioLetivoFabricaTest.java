package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.negocio.pedagogico.CalendarioLetivoDao;

public class CalendarioLetivoFabricaTest {
	private static CalendarioLetivoFabricaTest instance = null;

	public static synchronized CalendarioLetivoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CalendarioLetivoFabricaTest();
		}
		return instance;
	}

	public CalendarioLetivo criaCalendarioLetivo(EntityManager em) {
		CalendarioLetivo c = new CalendarioLetivo();
		c.setAnoLetivo(Long.valueOf("2016"));
		c.setAtivo(Boolean.TRUE);
		c.setCalendarioPadrao(Boolean.TRUE);
		//
		c.setNome("nome");
		c.setSelecionarDomingo(Boolean.FALSE);
		c.setSelecionarSabado(Boolean.TRUE);
		//
		return c;
	}

	public CalendarioLetivo criaCalendarioLetivoPersistido(EntityManager em) {
		CalendarioLetivo c = new CalendarioLetivo();
		CalendarioLetivoDao calendarioLetivoDao = new CalendarioLetivoDao(em);
		calendarioLetivoDao.persist(c);
		return c;
	}
}
