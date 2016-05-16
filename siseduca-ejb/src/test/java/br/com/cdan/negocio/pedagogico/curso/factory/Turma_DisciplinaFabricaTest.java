package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.negocio.pedagogico.contrato.factory.EstagioMonografiaFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.Turma_DisciplinaDao;
import br.com.cdan.negocio.pedagogico.diario.factory.DiarioDeAulaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class Turma_DisciplinaFabricaTest {
	private static Turma_DisciplinaFabricaTest instance = null;

	public static synchronized Turma_DisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Turma_DisciplinaFabricaTest();
		}
		return instance;
	}

	public Turma_Disciplina criaTurma_Disciplina(EntityManager em) {
		Turma_Disciplina t = new Turma_Disciplina();
		t.setAno(Long.valueOf("2016"));
		t.setDataInicio(Calendar.getInstance());
		t.setDiaDaSemanaAula(DiaDaSemanaAulaFabricaTest.getInstance().criaDiaDaSemanaAulaPersistido(em));
		t.setDiarioDeAula(DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAulaPersistido(em));
		t.setDisciplina(DisciplinaFabricaTest.getInstance().criaDisciplinaPersistido(em));
		t.setEstagioMonografia(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografiaPersistido(em));
		t.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		t.setSalaDeAula("salaDeAula");
		t.setTipoDeInvestimento(TipoDeInvestimentoFabricaTest.getInstance().criaTipoDeInvestimentoPersistido(em));
		t.setTurma(TurmaFabricaTest.getInstance().criaTurmaPersistido(em));
		return t;
	}

	public Turma_Disciplina criaTurma_DisciplinaPersistido(EntityManager em) {
		Turma_Disciplina t = criaTurma_Disciplina(em);
		Turma_DisciplinaDao dao = new Turma_DisciplinaDao(em);
		dao.persist(t);
		return t;
	}

}
