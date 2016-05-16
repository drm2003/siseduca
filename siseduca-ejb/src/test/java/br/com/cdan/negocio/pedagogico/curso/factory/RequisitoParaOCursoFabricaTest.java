package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.RequisitoParaOCurso;
import br.com.cdan.negocio.pedagogico.curso.RequisitoParaOCursoDao;

public class RequisitoParaOCursoFabricaTest {
	private static RequisitoParaOCursoFabricaTest instance = null;

	public static synchronized RequisitoParaOCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new RequisitoParaOCursoFabricaTest();
		}
		return instance;
	}

	public RequisitoParaOCurso criaRequisitoParaOCurso(EntityManager em) {
		RequisitoParaOCurso r = new RequisitoParaOCurso();
		r.setAtivo(Boolean.TRUE);
		r.setCurso(CursoFabricaTest.getInstance().criaCursoPersistido(em));
		r.setDescricao("descricao");
		//
		return r;
	}

	public RequisitoParaOCurso criaRequisitoParaOCursoPersistido(EntityManager em) {
		RequisitoParaOCurso r = criaRequisitoParaOCurso(em);
		RequisitoParaOCursoDao dao = new RequisitoParaOCursoDao(em);
		//
		dao.persist(r);
		return r;
	}
}
