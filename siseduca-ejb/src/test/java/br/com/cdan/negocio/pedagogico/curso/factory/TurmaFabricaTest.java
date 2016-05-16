package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.negocio.pedagogico.curso.TurmaDao;
import br.com.cdan.negocio.pedagogico.factory.CalendarioLetivoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.HorarioDeAulaFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.SituacaoDaTurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class TurmaFabricaTest {
	private static TurmaFabricaTest instance = null;

	public static synchronized TurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TurmaFabricaTest();
		}
		return instance;
	}

	public Turma criaTurma(EntityManager em) {
		Turma t = new Turma();
		t.setAtivo(Boolean.TRUE);
		t.setCalendarioPadrao(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivoPersistido(em));
		t.setCodigo(Long.valueOf("10"));
		t.setCurso(CursoFabricaTest.getInstance().criaCursoPersistido(em));
		t.setDataInicio(Calendar.getInstance());
		t.setDataTermino(Calendar.getInstance());
		t.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAulaPersistido(em));
		t.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricularPersistido(em));
		t.setNome("nome");
		t.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		t.setSalaDeAulaPadrao("salaDeAulaPadrao");
		t.setSigla("sigla");
		t.setSituacaoDaTurma(SituacaoDaTurmaFabricaTest.getInstance().criaSituacaoDaTurmaPersistido(em));
		return t;
	}

	public Turma criaTurmaPersistido(EntityManager em) {
		Turma t = criaTurma(em);
		TurmaDao dao = new TurmaDao(em);
		dao.persist(t);
		return t;
	}
}
