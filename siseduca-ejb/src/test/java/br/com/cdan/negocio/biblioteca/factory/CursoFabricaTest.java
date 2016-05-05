package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;
import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;
import br.com.cdan.model.pedagogico.curso.Turma;

public class CursoFabricaTest {
	private static CursoFabricaTest instance = null;

	public static synchronized CursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CursoFabricaTest();
		}
		return instance;
	}

	public Curso criaCurso() {
		Curso c = new Curso();
		c.setAtivo(Boolean.TRUE);
		c.setAtoOficial("atoOficial");
		c.setCompartilhado(Boolean.TRUE);
		c.setCoordenador(UsuarioFabricaTest.getInstance().criaUsuario());
		c.setCurriculoDoCurso("curriculoDoCurso");
		//
		Set<Curso_MatrizCurricular> cursos_matrizesCurricular = new LinkedHashSet<>();
		cursos_matrizesCurricular.add(Curso_MatrizCurricularFabricaTest.getInstance().criaCurso_MatrizCurricular());
		cursos_matrizesCurricular.add(Curso_MatrizCurricularFabricaTest.getInstance().criaCurso_MatrizCurricular());
		c.setCursos_MatrizesCurricular(cursos_matrizesCurricular);
		c.setIdadeMinima(Long.valueOf("10"));
		c.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado());
		c.setModular(Boolean.TRUE);
		c.setNome("nome");
		c.setNomeInstituicao("nomeInstituicao");
		c.setNumeroDeModulos("numeroDeModulos");
		c.setNumeroDeVagas("numeroDeVagas");
		c.setNumeroMaximoDeDependencias(Long.valueOf("2"));
		c.setPerfilDoCurso("perfilDoCurso");
		//
		Set<PlanoFinanceiroDoCurso> planosFinanceiroDoCurso = new LinkedHashSet<>();
		planosFinanceiroDoCurso.add(PlanoFinanceiroDoCursoFabricaTest.getInstance().criaPlanoFinanceiroDoCurso());
		planosFinanceiroDoCurso.add(PlanoFinanceiroDoCursoFabricaTest.getInstance().criaPlanoFinanceiroDoCurso());
		c.setPlanosFinanceiroDoCurso(planosFinanceiroDoCurso);
		//
		c.setPontoDeEquilibrio("pontoDeEquilibrio");
		c.setReconhecimentoDoCurso("reconhecimentoDoCurso");
		//
		Set<RequisitoParaOCurso> requisitosParaOCurso = new LinkedHashSet<>();
		requisitosParaOCurso.add(RequisitoParaOCursoFabricaTest.getInstance().criaRequisitoParaOCurso());
		requisitosParaOCurso.add(RequisitoParaOCursoFabricaTest.getInstance().criaRequisitoParaOCurso());
		c.setRequisitosParaOCurso(requisitosParaOCurso);
		//
		c.setSerieEquivalente(SeriePadraoFabricaTest.getInstance().criaSeriePadrao());
		c.setSigla("sigla");
		c.setTempoMaximoParaConclusaoDoCurso(Long.valueOf("10"));
		c.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		//
		Set<Turma> turmas = new LinkedHashSet<>();
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		turmas.add(TurmaFabricaTest.getInstance().criaTurma());
		c.setTurmas(turmas);
		//
		return c;
	}

}
