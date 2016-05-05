package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.LinhaProgramatica;
import br.com.cdan.negocio.biblioteca.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.biblioteca.LinhaProgramaticaDao;

public class LinhaProgramaticaFabricaTest {
	private static LinhaProgramaticaFabricaTest instance = null;

	public static synchronized LinhaProgramaticaFabricaTest getInstance() {
		if (instance == null) {
			instance = new LinhaProgramaticaFabricaTest();
		}
		return instance;
	}

	public LinhaProgramatica criaLinhaProgramatica() {
		LinhaProgramatica l = new LinhaProgramatica();
		l.setAssunto("assunto");
		l.setAtivo(Boolean.TRUE);
		l.setAula(Long.valueOf("10"));
		l.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		l.setSubunidade("subunidade");
		l.setUnidade("unidade");
		return l;
	}

	public LinhaProgramatica criaLinhaProgramaticaPersistido(EntityManager em) {
		LinhaProgramatica l = criaLinhaProgramatica();
		LinhaProgramaticaDao dao = new LinhaProgramaticaDao();
		//
		Disciplina_MatrizCurricular disciplina_MatrizCurricular = l.getDisciplina_MatrizCurricular();
		Disciplina_MatrizCurricularDao disciplina_MatrizCurricularDao = new Disciplina_MatrizCurricularDao();
		disciplina_MatrizCurricularDao.persist(disciplina_MatrizCurricular);
		l.setDisciplina_MatrizCurricular(disciplina_MatrizCurricular);
		//
		dao.persist(l);
		return l;
	}
}
