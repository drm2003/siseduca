package br.com.cdan.negocio.pedagogico.curso.factory;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.model.pedagogico.TipoDeDisciplina;
import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.model.pedagogico.curso.Disciplina_MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.negocio.pedagogico.TipoDeCursoDao;
import br.com.cdan.negocio.pedagogico.TipoDeDisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.Disciplina_MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.curso.Turma_DisciplinaDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeCursoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.TipoDeDisciplinaFabricaTest;

public class DisciplinaFabricaTest {
	private static DisciplinaFabricaTest instance = null;

	public static synchronized DisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DisciplinaFabricaTest();
		}
		return instance;
	}

	public Disciplina criaDisciplina() {
		Disciplina d = new Disciplina();
		d.setAtivo(Boolean.TRUE);
		d.setCodigoINEP("codigoINEP");
		d.setCompartilhado(Boolean.TRUE);
		d.setDisciplinaDependente(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		// Disciplinas e Matriz curricular
		Set<Disciplina_MatrizCurricular> disciplinas_MatrizesCurricular = new LinkedHashSet<>();
		disciplinas_MatrizesCurricular
				.add(Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		disciplinas_MatrizesCurricular
				.add(Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricular());
		d.setDisciplinas_MatrizCurricular(disciplinas_MatrizesCurricular);
		//
		d.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		d.setNome("nome");
		d.setSigla("sigla");
		d.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		d.setTipoDeDisciplina(TipoDeDisciplinaFabricaTest.getInstance().criaTipoDeDisciplina());
		// Turmas e disciplinas
		Set<Turma_Disciplina> turmas_Disciplinas = new LinkedHashSet<>();
		turmas_Disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turmas_Disciplinas.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		d.setTurma_Disciplina(turmas_Disciplinas);
		//
		d.setValorHoraAula(BigDecimal.TEN);
		return d;
	}

	public Disciplina criaDisciplinaPersistido(EntityManager em) {
		Disciplina d = criaDisciplina();
		DisciplinaDao dao = new DisciplinaDao(em);
		//
		MatrizCurricularDao matrizCurricularDao = new MatrizCurricularDao(em);
		MatrizCurricular disciplinaDependente = d.getDisciplinaDependente();
		matrizCurricularDao.persist(disciplinaDependente);
		d.setDisciplinaDependente(disciplinaDependente);
		// Disciplinas e Matriz curricular
		Set<Disciplina_MatrizCurricular> disciplinas_MatrizesCurricular = new LinkedHashSet<>();
		Disciplina_MatrizCurricularDao disciplina_MatrizCurricularDao = new Disciplina_MatrizCurricularDao(em);
		d.getDisciplinas_MatrizCurricular().forEach(disciplina_MatrizCurricular -> {
			disciplina_MatrizCurricularDao.persist(disciplina_MatrizCurricular);
			disciplinas_MatrizesCurricular.add(disciplina_MatrizCurricular);
		});
		d.setDisciplinas_MatrizCurricular(disciplinas_MatrizesCurricular);
		//
		MatrizCurricular matrizCurricular = d.getMatrizCurricular();
		matrizCurricularDao.persist(matrizCurricular);
		d.setMatrizCurricular(matrizCurricular);
		//
		TipoDeCursoDao tipoDeCursoDao = new TipoDeCursoDao(em);
		TipoDeCurso tipoDeCurso = d.getTipoDeCurso();
		tipoDeCursoDao.persist(tipoDeCurso);
		d.setTipoDeCurso(tipoDeCurso);
		//
		TipoDeDisciplinaDao tipoDeDisciplinaDao = new TipoDeDisciplinaDao(em);
		TipoDeDisciplina tipoDeDisciplina = d.getTipoDeDisciplina();
		tipoDeDisciplinaDao.persist(tipoDeDisciplina);
		d.setTipoDeDisciplina(tipoDeDisciplina);
		// Turmas e disciplinas
		Set<Turma_Disciplina> turmas_Disciplinas = new LinkedHashSet<>();
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		d.getTurma_Disciplina().forEach(turma_Disciplina -> {
			turma_DisciplinaDao.persist(turma_Disciplina);
			turmas_Disciplinas.add(turma_Disciplina);
		});
		d.setTurma_Disciplina(turmas_Disciplinas);
		//
		dao.persist(d);
		return d;
	}

}
