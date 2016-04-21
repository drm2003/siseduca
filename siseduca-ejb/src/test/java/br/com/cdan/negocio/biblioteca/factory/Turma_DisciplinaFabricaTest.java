package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

public class Turma_DisciplinaFabricaTest {
	private static Turma_DisciplinaFabricaTest instance = null;

	public static synchronized Turma_DisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new Turma_DisciplinaFabricaTest();
		}
		return instance;
	}

	public Turma_Disciplina criaTurma_Disciplina() {
		Turma_Disciplina t = new Turma_Disciplina();
		t.setAno(Long.valueOf("2016"));
		t.setDataInicio(Calendar.getInstance());
		t.setDiaDaSemanaAula(DiaDaSemanaAulaFabricaTest.getInstance().criaDiaDaSemanaAula());
		t.setDiarioDeAula(DiarioDeAulaFabricaTest.getInstance().criaDiarioDeAula());
		t.setDisciplina(DisciplinaFabricaTest.getInstance().criaDisciplina());
		t.setEstagioMonografia(EstagioMonografiaFabricaTest.getInstance().criaEstagioMonografia());
		// Matriculas
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		t.setMatriculas(matriculas);
		//
		t.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		t.setSalaDeAula("salaDeAula");
		t.setTipoDeInvestimento(TipoDeInvestimentoFabricaTest.getInstance().criaTipoDeInvestimento());
		t.setTurma(TurmaFabricaTest.getInstance().criaTurma());
		return t;
	}

}
