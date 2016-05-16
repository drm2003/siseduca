package br.com.cdan.negocio.pedagogico.curso.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.factory.SistemaDeAvaliacaoFabricaTest;

public class Disciplina_MatrizCurricularFabricaTest {
	private static Disciplina_MatrizCurricularFabricaTest instance = null;

	public static synchronized Disciplina_MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new Disciplina_MatrizCurricularFabricaTest();
		}
		return instance;
	}

	public Disciplina_MatrizCurricular criaDisciplina_MatrizCurricular(EntityManager em) {
		Disciplina_MatrizCurricular d = new Disciplina_MatrizCurricular();
		d.setCartaHorariaPratica(Calendar.getInstance().getTime());
		d.setCartaHorariaSemanal(Calendar.getInstance().getTime());
		d.setCartaHorariaTotal(Calendar.getInstance().getTime());
		d.setCodigo(Long.valueOf("10"));
		d.setDescricao("descricao");
		d.setDisciplina(DisciplinaFabricaTest.getInstance().criaDisciplinaPersistido(em));
		d.setDisciplinaOptativa(Boolean.FALSE);
		d.setEquivalencia("equivalencia");
		d.setEquivalencias("equivalencias");
		//
		d.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricularPersistido(em));
		d.setMetodologia(MetodologiaFabricaTest.getInstance().criaMetodologiaPersistido(em));
		d.setNumeroModulo(Long.valueOf("5"));
		d.setOrdem(Long.valueOf("3"));
		d.setPlanoDeEnsino(PlanoDeEnsinoFabricaTest.getInstance().criaPlanoDeEnsinoPersistido(em));
		d.setRequisitos("requisitos");
		d.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacaoPersistido(em));
		return d;
	}

	public Disciplina_MatrizCurricular criaDisciplina_MatrizCurricularPersistido(EntityManager em) {
		Disciplina_MatrizCurricular d = criaDisciplina_MatrizCurricular(em);
		Disciplina_MatrizCurricularDao dao = new Disciplina_MatrizCurricularDao(em);
		//
		dao.persist(d);
		return d;
	}

}
