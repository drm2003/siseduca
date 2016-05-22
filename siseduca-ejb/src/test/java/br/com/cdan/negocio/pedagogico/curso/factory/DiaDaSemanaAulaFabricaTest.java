package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumDiaDaSemana;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.negocio.pedagogico.curso.DiaDaSemanaAulaDao;

public class DiaDaSemanaAulaFabricaTest {
	private static DiaDaSemanaAulaFabricaTest instance = null;

	public static synchronized DiaDaSemanaAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DiaDaSemanaAulaFabricaTest();
		}
		return instance;
	}

	public DiaDaSemanaAula criaDiaDaSemanaAula(EntityManager em) {
		DiaDaSemanaAula d = new DiaDaSemanaAula();
		d.setAtivo(Boolean.TRUE);
		d.setDiaDaSemana(EnumDiaDaSemana.SEGUNDA);
		d.setHoraInicio(Calendar.getInstance().getTime());
		d.setHoraTermino(Calendar.getInstance().getTime());
		// Turma e Disciplina
		d.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_DisciplinaPersistido(em));
		return d;
	}

	public DiaDaSemanaAula criaDiaDaSemanaAulaPersistido(EntityManager em) {
		DiaDaSemanaAula d = criaDiaDaSemanaAula(em);
		DiaDaSemanaAulaDao diaDaSemanaAulaDao = new DiaDaSemanaAulaDao(em);
		diaDaSemanaAulaDao.setEntityManager(em);
		// Turma e Disciplina
		diaDaSemanaAulaDao.persist(d);
		return d;
	}
}