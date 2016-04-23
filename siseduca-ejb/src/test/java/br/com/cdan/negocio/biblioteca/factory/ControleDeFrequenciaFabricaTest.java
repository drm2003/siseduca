package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.CalendarioLetivo;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.CalendarioLetivoDao;
import br.com.cdan.negocio.biblioteca.ControleDeFrequenciaDao;
import br.com.cdan.negocio.biblioteca.HorarioDeAulaDao;

public class ControleDeFrequenciaFabricaTest {
	private static ControleDeFrequenciaFabricaTest instance = null;

	public static synchronized ControleDeFrequenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new ControleDeFrequenciaFabricaTest();
		}
		return instance;
	}

	public ControleDeFrequencia criaControleDeFrequencia() {
		ControleDeFrequencia c = new ControleDeFrequencia();
		c.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		c.setAtivo(Boolean.TRUE);
		c.setCalendarioLetivo(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		c.setData(Calendar.getInstance());
		c.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAula());
		c.setPresenca(Boolean.TRUE);
		return c;
	}

	public ControleDeFrequencia criaControleDeFrequenciaPersistido(EntityManager em) {
		ControleDeFrequencia c = criaControleDeFrequencia();
		ControleDeFrequenciaDao controleDeFrequenciaDao = new ControleDeFrequenciaDao();
		controleDeFrequenciaDao.setEntityManager(em);
		// Aluno
		AlunoDao alunoDao = new AlunoDao();
		alunoDao.setEntityManager(em);
		Aluno aluno = c.getAluno();
		alunoDao.persist(aluno);
		c.setAluno(aluno);
		// Calendário Letivo
		CalendarioLetivoDao calendarioLetivoDao = new CalendarioLetivoDao();
		calendarioLetivoDao.setEntityManager(em);
		CalendarioLetivo calendarioLetivo = c.getCalendarioLetivo();
		calendarioLetivoDao.persist(calendarioLetivo);
		c.setCalendarioLetivo(calendarioLetivo);
		// Horario de Aula
		HorarioDeAulaDao horarioDeAulaDao = new HorarioDeAulaDao();
		horarioDeAulaDao.setEntityManager(em);
		HorarioDeAula horarioDeAula = c.getHorarioDeAula();
		horarioDeAulaDao.persist(horarioDeAula);
		c.setHorarioDeAula(horarioDeAula);
		//
		controleDeFrequenciaDao.persist(c);
		return c;
	}
}
