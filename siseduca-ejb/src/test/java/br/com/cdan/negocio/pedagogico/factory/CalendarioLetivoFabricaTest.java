package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.HorarioDeAcesso;
import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.model.pessoa.FeriadoEvento;
import br.com.cdan.negocio.acesso.HorarioDeAcessoDao;
import br.com.cdan.negocio.acesso.factory.HorarioDeAcessoFabricaTest;
import br.com.cdan.negocio.pedagogico.CalendarioLetivoDao;
import br.com.cdan.negocio.pedagogico.curso.TurmaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.diario.ControleDeFrequenciaDao;
import br.com.cdan.negocio.pedagogico.diario.factory.ControleDeFrequenciaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.FeriadoEventoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FeriadoEventoFabricaTest;

public class CalendarioLetivoFabricaTest {
	private static CalendarioLetivoFabricaTest instance = null;

	public static synchronized CalendarioLetivoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CalendarioLetivoFabricaTest();
		}
		return instance;
	}

	public CalendarioLetivo criaCalendarioLetivo() {
		CalendarioLetivo c = new CalendarioLetivo();
		c.setAnoLetivo(Long.valueOf("2016"));
		c.setAtivo(Boolean.TRUE);
		c.setCalendarioPadrao(Boolean.TRUE);
		// Controle de Frequencia
		Set<ControleDeFrequencia> controlesDeFrequencia = new LinkedHashSet<>();
		controlesDeFrequencia.add(ControleDeFrequenciaFabricaTest.getInstance().criaControleDeFrequencia());
		controlesDeFrequencia.add(ControleDeFrequenciaFabricaTest.getInstance().criaControleDeFrequencia());
		c.setControleDeFrequencia(controlesDeFrequencia);
		// Feriados eventos
		Set<FeriadoEvento> feriadosEvento = new LinkedHashSet<>();
		feriadosEvento.add(FeriadoEventoFabricaTest.getInstance().criaFeriadoEvento());
		feriadosEvento.add(FeriadoEventoFabricaTest.getInstance().criaFeriadoEvento());
		c.setFeriadoEvento(feriadosEvento);
		// Horário de Acesso
		Set<HorarioDeAcesso> horariosDeAcesso = new LinkedHashSet<>();
		horariosDeAcesso.add(HorarioDeAcessoFabricaTest.getInstance().criaHorarioDeAcesso());
		horariosDeAcesso.add(HorarioDeAcessoFabricaTest.getInstance().criaHorarioDeAcesso());
		c.setHorarioDeAcesso(horariosDeAcesso);
		//
		c.setNome("nome");
		c.setSelecionarDomingo(Boolean.FALSE);
		c.setSelecionarSabado(Boolean.TRUE);
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		c.setTurmas(turmas);
		//
		return c;
	}

	public CalendarioLetivo criaCalendarioLetivoPersistido(EntityManager em) {
		CalendarioLetivo c = new CalendarioLetivo();
		CalendarioLetivoDao calendarioLetivoDao = new CalendarioLetivoDao();
		calendarioLetivoDao.setEntityManager(em);
		// Controle de Frequencia
		Set<ControleDeFrequencia> controlesDeFrequencia = new LinkedHashSet<>();
		ControleDeFrequenciaDao controleDeFrequenciaDao = new ControleDeFrequenciaDao(em);
		controleDeFrequenciaDao.setEntityManager(em);
		c.getControleDeFrequencia().forEach(controleDeFrequencia -> {
			controleDeFrequenciaDao.persist(controleDeFrequencia);
			controlesDeFrequencia.add(controleDeFrequencia);
		});
		c.setControleDeFrequencia(controlesDeFrequencia);
		// Feriados eventos
		Set<FeriadoEvento> feriadosEvento = new LinkedHashSet<>();
		FeriadoEventoDao feriadoEventoDao = new FeriadoEventoDao();
		feriadoEventoDao.setEntityManager(em);
		c.getFeriadoEvento().forEach(feriadoEvento -> {
			feriadoEventoDao.persist(feriadoEvento);
			feriadosEvento.add(feriadoEvento);
		});
		c.setFeriadoEvento(feriadosEvento);
		// Horário de Acesso
		Set<HorarioDeAcesso> horariosDeAcesso = new LinkedHashSet<>();
		HorarioDeAcessoDao horarioDeAcessoDao = new HorarioDeAcessoDao();
		horarioDeAcessoDao.setEntityManager(em);
		c.getHorarioDeAcesso().forEach(horarioDeAcesso -> {
			horarioDeAcessoDao.persist(horarioDeAcesso);
			horariosDeAcesso.add(horarioDeAcesso);
		});
		c.setHorarioDeAcesso(horariosDeAcesso);
		//
		// Turmas
		Set<Turma> turmas = new LinkedHashSet<>();
		TurmaDao turmaDao = new TurmaDao();
		turmaDao.setEntityManager(em);
		c.getTurmas().forEach(turma -> {
			turmaDao.persist(turma);
			turmas.add(turma);
		});
		c.setTurmas(turmas);
		//
		calendarioLetivoDao.persist(c);
		return c;
	}
}
