package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.negocio.biblioteca.DiaDaSemanaAulaDao;
import br.com.cdan.negocio.biblioteca.DiarioDeAulaDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;

public class DiaDaSemanaAulaFabricaTest {
	private static DiaDaSemanaAulaFabricaTest instance = null;

	public static synchronized DiaDaSemanaAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DiaDaSemanaAulaFabricaTest();
		}
		return instance;
	}

	public DiaDaSemanaAula criaDiaDaSemanaAula() {
		DiaDaSemanaAula d = new DiaDaSemanaAula();
		d.setDiaDaSemana(EnumDiaDaSemana.SEGUNDA);
		d.setHoraInicio(Calendar.getInstance().getTime());
		d.setHoraTermino(Calendar.getInstance().getTime());
		// Turma e Disciplina
		d.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		// Diarios de Aula
		Set<DiarioDeAula> diariosDeAula = new LinkedHashSet<>();
		diariosDeAula.add(DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAula());
		diariosDeAula.add(DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAula());
		d.setDiariosDeAula(diariosDeAula);
		return d;
	}

	public DiaDaSemanaAula criaDiaDaSemanaAulaPersistido(EntityManager em) {
		DiaDaSemanaAula d = criaDiaDaSemanaAula();
		DiaDaSemanaAulaDao diaDaSemanaAulaDao = new DiaDaSemanaAulaDao();
		diaDaSemanaAulaDao.setEntityManager(em);
		// Turma e Disciplina
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao();
		turma_DisciplinaDao.setEntityManager(em);
		Turma_Disciplina turma_Disciplina = d.getTurma_Disciplina();
		turma_DisciplinaDao.persist(turma_Disciplina);
		d.setTurma_Disciplina(turma_Disciplina);
		// Diarios de Aula
		Set<DiarioDeAula> diariosDeAula = new LinkedHashSet<>();
		DiarioDeAulaDao diarioDeAulaDao = new DiarioDeAulaDao();
		diarioDeAulaDao.setEntityManager(em);
		d.getDiariosDeAula().forEach(diarioDeAula -> {
			diarioDeAulaDao.persist(diarioDeAula);
			diariosDeAula.add(diarioDeAula);
		});
		d.setDiariosDeAula(diariosDeAula);
		//
		diaDaSemanaAulaDao.persist(d);
		return d;
	}
}
