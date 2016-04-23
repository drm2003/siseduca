package br.com.cdan.negocio.biblioteca.factory;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;

public class DisciplinaFabricaTest {
	private static DisciplinaFabricaTest instance = null;

	public static synchronized DisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DisciplinaFabricaTest();
		}
		return instance;
	}

	public Disciplina criaDisciplina() {
		Disciplina d = new Disciplina();
		d.setAtivo(Boolean.TRUE);
		d.setCodigoINEP("codigoINEP");
		d.setCompartilhado(Boolean.TRUE);
		d.setDisciplinaDependente(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		// Disciplinas e Matriz curricular
		Set<Disciplina_MatrizCurricular> disciplinas_MatrizesCurricular = new LinkedHashSet<>();
		disciplinas_MatrizesCurricular
				.add(Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		disciplinas_MatrizesCurricular
				.add(Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		d.setDisciplinas_MatrizCurricular(disciplinas_MatrizesCurricular);
		//
		d.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		d.setNome("nome");
		d.setSigla("sigla");
		d.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		d.setTipoDeDisciplina(TipoDeDisciplinaFabricaTest.getInstance().criaTipoDeDisciplina());
		// Turmas e disciplinas
		Set<Turma_Disciplina> turmas_Disciplinas = new LinkedHashSet<>();
		turmas_Disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turmas_Disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		d.setTurma_Disciplina(turmas_Disciplinas);
		//
		d.setValorHoraAula(BigDecimal.TEN);
		return d;
	}
}
