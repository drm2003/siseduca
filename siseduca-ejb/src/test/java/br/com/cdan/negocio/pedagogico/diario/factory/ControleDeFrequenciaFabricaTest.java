package br.com.cdan.negocio.pedagogico.diario.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.negocio.pedagogico.diario.ControleDeFrequenciaDao;
import br.com.cdan.negocio.pedagogico.factory.CalendarioLetivoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.HorarioDeAulaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

public class ControleDeFrequenciaFabricaTest {
	private static ControleDeFrequenciaFabricaTest instance = null;

	public static synchronized ControleDeFrequenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ControleDeFrequenciaFabricaTest();
		}
		return instance;
	}

	public ControleDeFrequencia criaControleDeFrequencia(EntityManager em) {
		ControleDeFrequencia c = new ControleDeFrequencia();
		c.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		c.setAtivo(Boolean.TRUE);
		c.setCalendarioLetivo(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivoPersistido(em));
		c.setData(Calendar.getInstance());
		c.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAulaPersistido(em));
		c.setPresenca(Boolean.TRUE);
		return c;
	}

	public ControleDeFrequencia criaControleDeFrequenciaPersistido(EntityManager em) {
		ControleDeFrequencia c = criaControleDeFrequencia(em);
		ControleDeFrequenciaDao controleDeFrequenciaDao = new ControleDeFrequenciaDao(em);
		controleDeFrequenciaDao.persist(c);
		return c;
	}
}
