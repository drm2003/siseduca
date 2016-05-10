package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.negocio.biblioteca.CursoDao;
import br.com.cdan.negocio.biblioteca.Curso_MatrizCurricularDao;
import br.com.cdan.negocio.biblioteca.MatrizCurricularDao;

public class Curso_MatrizCurricularFabricaTest {
	private static Curso_MatrizCurricularFabricaTest instance = null;

	public static synchronized Curso_MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new Curso_MatrizCurricularFabricaTest();
		}
		return instance;
	}

	public Curso_MatrizCurricular criaCurso_MatrizCurricular() {
		Curso_MatrizCurricular c = new Curso_MatrizCurricular();
		c.setCurso(CursoFabricaTest.getInstance().criaCurso());
		c.setDataValidade(Calendar.getInstance());
		c.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		return c;
	}

	public Curso_MatrizCurricular criaCurso_MatrizCurricularPersistido(EntityManager em) {
		Curso_MatrizCurricular c = criaCurso_MatrizCurricular();
		Curso_MatrizCurricularDao dao = new Curso_MatrizCurricularDao(em);
		//
		Curso curso = c.getCurso();
		CursoDao cursoDao = new CursoDao(em);
		cursoDao.persist(curso);
		c.setCurso(curso);
		//
		MatrizCurricularDao matrizCurricularDao = new MatrizCurricularDao(em);
		MatrizCurricular matrizCurricular = c.getMatrizCurricular();
		matrizCurricularDao.persist(matrizCurricular);
		c.setMatrizCurricular(matrizCurricular);
		//
		dao.persist(c);
		return c;
	}
}
