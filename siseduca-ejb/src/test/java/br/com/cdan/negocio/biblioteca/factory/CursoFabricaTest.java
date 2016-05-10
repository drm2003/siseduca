package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.acesso.Usuario;
import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;
import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.negocio.biblioteca.CursoDao;
import br.com.cdan.negocio.biblioteca.Curso_MatrizCurricularDao;
import br.com.cdan.negocio.biblioteca.InteressadoDao;
import br.com.cdan.negocio.biblioteca.PlanoFinanceiroDoCursoDao;
import br.com.cdan.negocio.biblioteca.RequisitoParaOCursoDao;
import br.com.cdan.negocio.biblioteca.SeriePadraoDao;
import br.com.cdan.negocio.biblioteca.TipoDeCursoDao;
import br.com.cdan.negocio.biblioteca.TurmaDao;
import br.com.cdan.negocio.biblioteca.UsuarioDao;

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

	public Curso criaCursoPersistido(EntityManager em) {
		Curso c = criaCurso();
		CursoDao dao = new CursoDao(em);
		//
		UsuarioDao usuarioDao = new UsuarioDao(em);
		Usuario coordenador = c.getCoordenador();
		usuarioDao.persist(coordenador);
		c.setCoordenador(coordenador);
		//
		Set<Curso_MatrizCurricular> cursos_matrizesCurricular = new LinkedHashSet<>();
		Curso_MatrizCurricularDao curso_MatrizCurricularDao = new Curso_MatrizCurricularDao(em);
		c.getCursos_MatrizesCurricular().forEach(curso_MatrizCurricular -> {
			curso_MatrizCurricularDao.persist(curso_MatrizCurricular);
			cursos_matrizesCurricular.add(curso_MatrizCurricular);
		});
		c.setCursos_MatrizesCurricular(cursos_matrizesCurricular);
		//
		InteressadoDao interessadoDao = new InteressadoDao(em);
		Interessado interessado = c.getInteressado();
		interessadoDao.persist(interessado);
		c.setInteressado(interessado);
		//
		Set<PlanoFinanceiroDoCurso> planosFinanceiroDoCurso = new LinkedHashSet<>();
		PlanoFinanceiroDoCursoDao planoFinanceiroDoCursoDao = new PlanoFinanceiroDoCursoDao(em);
		c.getPlanosFinanceiroDoCurso().forEach(planoFinanceiroDoCurso -> {
			planoFinanceiroDoCursoDao.persist(planoFinanceiroDoCurso);
			planosFinanceiroDoCurso.add(planoFinanceiroDoCurso);
		});
		c.setPlanosFinanceiroDoCurso(planosFinanceiroDoCurso);
		//
		Set<RequisitoParaOCurso> requisitosParaOCurso = new LinkedHashSet<>();
		RequisitoParaOCursoDao requisitoParaOCursoDao = new RequisitoParaOCursoDao(em);
		c.getRequisitosParaOCurso().forEach(requisitoParaOCurso -> {
			requisitoParaOCursoDao.persist(requisitoParaOCurso);
			requisitosParaOCurso.add(requisitoParaOCurso);
		});
		c.setRequisitosParaOCurso(requisitosParaOCurso);
		//
		SeriePadraoDao seriePadraoDao = new SeriePadraoDao(em);
		SeriePadrao serieEquivalente = c.getSerieEquivalente();
		seriePadraoDao.persist(serieEquivalente);
		c.setSerieEquivalente(serieEquivalente);
		//
		TipoDeCursoDao tipoDeCursoDao = new TipoDeCursoDao(em);
		TipoDeCurso tipoDeCurso = c.getTipoDeCurso();
		tipoDeCursoDao.persist(tipoDeCurso);
		c.setTipoDeCurso(tipoDeCurso);
		//
		Set<Turma> turmas = new LinkedHashSet<>();
		TurmaDao turmaDao = new TurmaDao(em);
		c.getTurmas().forEach(turma -> {
			turmaDao.persist(turma);
			turmas.add(turma);
		});
		c.setTurmas(turmas);
		//
		dao.persist(c);
		return c;
	}

}
