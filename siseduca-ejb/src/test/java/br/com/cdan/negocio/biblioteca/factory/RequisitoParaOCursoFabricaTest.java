package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;
import br.com.cdan.negocio.biblioteca.CursoDao;
import br.com.cdan.negocio.biblioteca.RequisitoParaOCursoDao;

public class RequisitoParaOCursoFabricaTest {
	private static RequisitoParaOCursoFabricaTest instance = null;

	public static synchronized RequisitoParaOCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new RequisitoParaOCursoFabricaTest();
		}
		return instance;
	}

	public RequisitoParaOCurso criaRequisitoParaOCurso() {
		RequisitoParaOCurso r = new RequisitoParaOCurso();
		r.setAtivo(Boolean.TRUE);
		r.setCurso(CursoFabricaTest.getInstance().criaCurso());
		r.setDescricao("descricao");
		//
		return r;
	}

	public RequisitoParaOCurso criaRequisitoParaOCursoPersistido(EntityManager em) {
		RequisitoParaOCurso r = criaRequisitoParaOCurso();
		RequisitoParaOCursoDao dao = new RequisitoParaOCursoDao(em);
		//
		Curso curso = r.getCurso();
		CursoDao cursoDao = new CursoDao(em);
		cursoDao.persist(curso);
		r.setCurso(curso);
		//
		dao.persist(r);
		return r;
	}
}
