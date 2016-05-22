package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Conteudo;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.curso.ConteudoDao;

public class ConteudoFabricaTest extends FabricaTest {
	private static ConteudoFabricaTest instance = null;

	public static synchronized ConteudoFabricaTest getInstance() {
		if (instance == null) {
			instance = new ConteudoFabricaTest();
		}
		return instance;
	}

	public Conteudo criaConteudo(EntityManager em) {
		Conteudo c = new Conteudo();
		c.setAtivo(Boolean.TRUE);
		c.setCargaHoraria(Long.valueOf("20"));
		c.setCargaHorariaEstagio(Long.valueOf("4"));
		c.setDescricao(criarStringDinamicaPorTamanho(250));
		c.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricularPersistido(em));
		return c;
	}

	public Conteudo criaConteudoPersistido(EntityManager em) {
		Conteudo c = criaConteudo(em);
		ConteudoDao dao = new ConteudoDao(em);
		//
		dao.persist(c);
		return c;
	}
}
