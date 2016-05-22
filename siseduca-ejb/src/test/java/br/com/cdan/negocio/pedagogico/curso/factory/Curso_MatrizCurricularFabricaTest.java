package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Curso_MatrizCurricularPK;
import br.com.cdan.negocio.pedagogico.curso.Curso_MatrizCurricularDao;

public class Curso_MatrizCurricularFabricaTest {
	private static Curso_MatrizCurricularFabricaTest instance = null;

	public static synchronized Curso_MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new Curso_MatrizCurricularFabricaTest();
		}
		return instance;
	}

	public Curso_MatrizCurricular criaCurso_MatrizCurricular(EntityManager em) {
		Curso_MatrizCurricular c = new Curso_MatrizCurricular();
		c.setAtivo(Boolean.TRUE);
		c.setCurso(CursoFabricaTest.getInstance().criaCursoPersistido(em));
		c.setDataValidade(Calendar.getInstance());
		c.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricularPersistido(em));
		c.setId(new Curso_MatrizCurricularPK(c.getCurso().getId(), c.getMatrizCurricular().getId()));
		return c;
	}

	public Curso_MatrizCurricular criaCurso_MatrizCurricularPersistido(EntityManager em) {
		Curso_MatrizCurricular c = criaCurso_MatrizCurricular(em);
		Curso_MatrizCurricularDao dao = new Curso_MatrizCurricularDao(em);
		//
		dao.persist(c);
		return c;
	}
}
