package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;

public class MatrizCurricularFabricaTest {
	private static MatrizCurricularFabricaTest instance = null;

	public static synchronized MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new MatrizCurricularFabricaTest();
		}
		return instance;
	}
	
	public MatrizCurricular criaMatrizCurricular(){
		MatrizCurricular m = new MatrizCurricular();
		m.setAtivo(Boolean.TRUE);
		//
		Set<Curso_MatrizCurricular> cursosMatrizCurricular = new LinkedHashSet<>();
		cursosMatrizCurricular.add(Curso_MatrizCurricularFabricaTest.getInstance().criaCurso_MatrizCurricular());
		cursosMatrizCurricular.add(Curso_MatrizCurricularFabricaTest.getInstance().criaCurso_MatrizCurricular());
		m.setCurso_MatrizCurricular(cursosMatrizCurricular);
		//
		Set<Disciplina_MatrizCurricular> disciplinasMatrizCurricular = new LinkedHashSet<>();
		disciplinasMatrizCurricular.add(Disciplina_MatrizCurricularFabricaTest.getInstance.criaDisciplina_MatrizCurricular());
		disciplinasMatrizCurricular.add(Disciplina_MatrizCurricularFabricaTest.getInstance.criaDisciplina_MatrizCurricular());
		m.setDisciplina_MatrizCurricular(disciplinasMatrizCurricular);
		//
		m.setNome("nome");
		m.setQuantidadeModulo(Long.valueOf("3"));
		m.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		m.setTurmas(turmas);
		return m;
	}

}
