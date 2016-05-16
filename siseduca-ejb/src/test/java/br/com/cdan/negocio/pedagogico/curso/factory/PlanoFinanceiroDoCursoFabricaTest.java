package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;
import br.com.cdan.negocio.pedagogico.curso.PlanoFinanceiroDoCursoDao;

public class PlanoFinanceiroDoCursoFabricaTest {
	private static PlanoFinanceiroDoCursoFabricaTest instance = null;

	public static synchronized PlanoFinanceiroDoCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoFinanceiroDoCursoFabricaTest();
		}
		return instance;
	}

	public PlanoFinanceiroDoCurso criaPlanoFinanceiroDoCurso(EntityManager em) {
		PlanoFinanceiroDoCurso p = new PlanoFinanceiroDoCurso();
		p.setAtivo(Boolean.TRUE);
		p.setCurso(CursoFabricaTest.getInstance().criaCursoPersistido(em));
		p.setDescricao("descricao");
		//
		p.setModulo(Long.valueOf("100"));
		p.setPlanoPadrao(Boolean.TRUE);
		return p;
	}

	public PlanoFinanceiroDoCurso criaPlanoFinanceiroDoCursoPersistido(EntityManager em) {
		PlanoFinanceiroDoCurso p = criaPlanoFinanceiroDoCurso(em);
		PlanoFinanceiroDoCursoDao dao = new PlanoFinanceiroDoCursoDao(em);
		dao.persist(p);
		return p;
	}
}
