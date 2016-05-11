package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.model.pedagogico.curso.Conteudo;
import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.LinhaProgramatica;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Metodologia;
import br.com.cdan.model.pedagogico.curso.PlanoDeEnsino;
import br.com.cdan.negocio.pedagogico.SistemaDeAvaliacaoDao;
import br.com.cdan.negocio.pedagogico.curso.ConteudoDao;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.LinhaProgramaticaDao;
import br.com.cdan.negocio.pedagogico.curso.MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.MetodologiaDao;
import br.com.cdan.negocio.pedagogico.curso.PlanoDeEnsinoDao;
import br.com.cdan.negocio.pedagogico.factory.SistemaDeAvaliacaoFabricaTest;

public class Disciplina_MatrizCurricularFabricaTest {
	private static Disciplina_MatrizCurricularFabricaTest instance = null;

	public static synchronized Disciplina_MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new Disciplina_MatrizCurricularFabricaTest();
		}
		return instance;
	}

	public Disciplina_MatrizCurricular criaDisciplina_MatrizCurricular() {
		Disciplina_MatrizCurricular d = new Disciplina_MatrizCurricular();
		d.setCartaHorariaPratica(Calendar.getInstance().getTime());
		d.setCartaHorariaSemanal(Calendar.getInstance().getTime());
		d.setCartaHorariaTotal(Calendar.getInstance().getTime());
		d.setCodigo(Long.valueOf("10"));
		//
		Set<Conteudo> conteudos = new LinkedHashSet<>();
		conteudos.add(ConteudoFabricaTest.getInstance().criaConteudo());
		conteudos.add(ConteudoFabricaTest.getInstance().criaConteudo());
		d.setConteudos(conteudos);
		//
		d.setDescricao("descricao");
		d.setDisciplina(DisciplinaFabricaTest.getInstance().criaDisciplina());
		d.setDisciplinaOptativa(Boolean.FALSE);
		d.setEquivalencia("equivalencia");
		d.setEquivalencias("equivalencias");
		// Linhas programáticas
		Set<LinhaProgramatica> linhasProgramaticas = new LinkedHashSet<>();
		linhasProgramaticas.add(LinhaProgramaticaFabricaTest.getInstance().criaLinhaProgramatica());
		linhasProgramaticas.add(LinhaProgramaticaFabricaTest.getInstance().criaLinhaProgramatica());
		d.setLinhasProgramaticas(linhasProgramaticas);
		//
		d.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		d.setMetodologia(MetodologiaFabricaTest.getInstance().criaMetodologia());
		d.setNumeroModulo(Long.valueOf("5"));
		d.setOrdem(Long.valueOf("3"));
		d.setPlanoDeEnsino(PlanoDeEnsinoFabricaTest.getInstance().criaPlanoDeEnsino());
		d.setRequisitos("requisitos");
		d.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacao());
		return d;
	}

	public Disciplina_MatrizCurricular criaDisciplina_MatrizCurricularPersistido(EntityManager em) {
		Disciplina_MatrizCurricular d = criaDisciplina_MatrizCurricular();
		Disciplina_MatrizCurricularDao dao = new Disciplina_MatrizCurricularDao(em);
		//
		ConteudoDao conteudoDao = new ConteudoDao(em);
		Set<Conteudo> conteudos = new LinkedHashSet<>();
		d.getConteudos().forEach(conteudo -> {
			conteudoDao.persist(conteudo);
			conteudos.add(conteudo);
		});
		d.setConteudos(conteudos);
		//
		DisciplinaDao disciplinaDao = new DisciplinaDao(em);
		Disciplina disciplina = d.getDisciplina();
		disciplinaDao.persist(disciplina);
		d.setDisciplina(disciplina);
		// Linhas programáticas
		LinhaProgramaticaDao linhaProgramaticaDao = new LinhaProgramaticaDao(em);
		Set<LinhaProgramatica> linhasProgramaticas = new LinkedHashSet<>();
		d.getLinhasProgramaticas().forEach(linhaProgramatica -> {
			linhaProgramaticaDao.persist(linhaProgramatica);
			linhasProgramaticas.add(linhaProgramatica);
		});
		d.setLinhasProgramaticas(linhasProgramaticas);
		//
		MatrizCurricularDao matrizCurricularDao = new MatrizCurricularDao(em);
		MatrizCurricular matrizCurricular = d.getMatrizCurricular();
		matrizCurricularDao.persist(matrizCurricular);
		d.setMatrizCurricular(matrizCurricular);
		//
		MetodologiaDao metodologiaDao = new MetodologiaDao(em);
		Metodologia metodologia = d.getMetodologia();
		metodologiaDao.persist(metodologia);
		d.setMetodologia(metodologia);
		//
		PlanoDeEnsinoDao planoDeEnsinoDao = new PlanoDeEnsinoDao(em);
		PlanoDeEnsino planoDeEnsino = d.getPlanoDeEnsino();
		planoDeEnsinoDao.persist(planoDeEnsino);
		d.setPlanoDeEnsino(planoDeEnsino);
		//
		SistemaDeAvaliacaoDao sistemaDeAvaliacaoDao = new SistemaDeAvaliacaoDao(em);
		SistemaDeAvaliacao sistemaDeAvaliacao = d.getSistemaDeAvaliacao();
		sistemaDeAvaliacaoDao.persist(sistemaDeAvaliacao);
		d.setSistemaDeAvaliacao(sistemaDeAvaliacao);
		//
		dao.persist(d);
		return d;
	}

}
