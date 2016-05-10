package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.curso.PlanoFinanceiroDoCurso;
import br.com.cdan.negocio.biblioteca.CursoDao;
import br.com.cdan.negocio.biblioteca.InvestimentoDao;
import br.com.cdan.negocio.biblioteca.PlanoFinanceiroDoCursoDao;

public class PlanoFinanceiroDoCursoFabricaTest {
	private static PlanoFinanceiroDoCursoFabricaTest instance = null;

	public static synchronized PlanoFinanceiroDoCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new PlanoFinanceiroDoCursoFabricaTest();
		}
		return instance;
	}

	public PlanoFinanceiroDoCurso criaPlanoFinanceiroDoCurso() {
		PlanoFinanceiroDoCurso p = new PlanoFinanceiroDoCurso();
		p.setAtivo(Boolean.TRUE);
		p.setCurso(CursoFabricaTest.getInstance().criaCurso());
		p.setDescricao("descricao");
		//
		Set<Investimento> investimentos = new LinkedHashSet<>();
		investimentos.add(InvestimentoFabricaTest.getInstance().criaInvestimento());
		investimentos.add(InvestimentoFabricaTest.getInstance().criaInvestimento());
		p.setInvestimentos(investimentos);
		//
		p.setModulo(Long.valueOf("100"));
		p.setPlanoPadrao(Boolean.TRUE);
		return p;
	}

	public PlanoFinanceiroDoCurso criaPlanoFinanceiroDoCursoPersistido(EntityManager em) {
		PlanoFinanceiroDoCurso p = criaPlanoFinanceiroDoCurso();
		PlanoFinanceiroDoCursoDao dao = new PlanoFinanceiroDoCursoDao(em);
		//
		CursoDao cursoDao = new CursoDao(em);
		Curso curso = p.getCurso();
		cursoDao.persist(curso);
		p.setCurso(curso);
		//
		Set<Investimento> investimentos = new LinkedHashSet<>();
		InvestimentoDao investimentoDao = new InvestimentoDao(em);
		p.getInvestimentos().forEach(investimento -> {
			investimentoDao.persist(investimento);
			investimentos.add(investimento);
		});
		p.setInvestimentos(investimentos);
		//
		dao.persist(p);
		return p;
	}
}
