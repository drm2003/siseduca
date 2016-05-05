package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Conteudo;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.negocio.biblioteca.ConteudoDao;
import br.com.cdan.negocio.biblioteca.Disciplina_MatrizCurricularDao;

public class ConteudoFabricaTest {
	private static ConteudoFabricaTest instance = null;

	public static synchronized ConteudoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ConteudoFabricaTest();
		}
		return instance;
	}

	public Conteudo criaConteudo() {
		Conteudo c = new Conteudo();
		c.setAtivo(Boolean.TRUE);
		c.setCargaHoraria(Long.valueOf("20"));
		c.setCargaHorariaEstagio(Long.valueOf("4"));
		c.setDescricao("descricao");
		c.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		return c;
	}

	public Conteudo criaConteudoPersistido(EntityManager em) {
		Conteudo c = criaConteudo();
		ConteudoDao dao = new ConteudoDao(em);
		//
		Disciplina_MatrizCurricularDao disciplina_MatrizCurricularDao = new Disciplina_MatrizCurricularDao(em);
		Disciplina_MatrizCurricular disciplina_MatrizCurricular = c.getDisciplina_MatrizCurricular();
		disciplina_MatrizCurricularDao.persist(disciplina_MatrizCurricular);
		c.setDisciplina_MatrizCurricular(disciplina_MatrizCurricular);
		//
		dao.persist(c);
		return c;
	}
}
