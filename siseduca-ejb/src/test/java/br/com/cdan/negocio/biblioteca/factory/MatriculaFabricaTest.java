package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SituacaoDoAlunoNaTurma;
import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;
import br.com.cdan.model.pedagogico.curso.Investimento;
import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.DisciplinaMatriculaDao;
import br.com.cdan.negocio.biblioteca.InvestimentoDao;
import br.com.cdan.negocio.biblioteca.MatriculaDao;
import br.com.cdan.negocio.biblioteca.SituacaoDoAlunoNaTurmaDao;
import br.com.cdan.negocio.biblioteca.SituacaoDoContratoDao;
import br.com.cdan.negocio.biblioteca.TipoDeContratoDao;

public class MatriculaFabricaTest {
	private static MatriculaFabricaTest instance = null;

	public static synchronized MatriculaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MatriculaFabricaTest();
		}
		return instance;
	}

	public Matricula criaMatricula() {
		Matricula m = new Matricula();
		m.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		m.setAtivo(Boolean.TRUE);
		m.setDataInicio(Calendar.getInstance());
		m.setDataTermino(Calendar.getInstance());
		// Disciplinas
		Set<DisciplinaMatricula> disciplinaMatriculas = new LinkedHashSet<>();
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		m.setDisciplinas(disciplinaMatriculas);
		//
		m.setInvestimento(InvestimentoFabricaTest.getInstance().criaInvestimento());
		m.setMatrizCurricular(Long.valueOf("10"));
		m.setNumeroContrato("numeroContrato");
		m.setSituacaoDoAlunoNaTurma(SituacaoDoAlunoNaTurmaFabricaTest.getInstance().criaSituacaoDoAlunoNaTurma());
		m.setSituacaoDoContrato(SituacaoDoContratoFabricaTest.getInstance().criaSituacaoDoContrato());
		m.setTipoDeContrato(TipoDeContratoFabricaTest.getInstance().criaTipoDeContrato());
		m.setTurma(Long.valueOf("10"));
		return m;
	}

	public Matricula criaMatriculaPersistido(EntityManager em) {
		Matricula m = criaMatricula();
		MatriculaDao dao = new MatriculaDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = m.getAluno();
		alunoDao.persist(aluno);
		m.setAluno(aluno);
		// Disciplinas
		Set<DisciplinaMatricula> disciplinasMatriculas = new LinkedHashSet<>();
		DisciplinaMatriculaDao disciplinaMatriculaDao = new DisciplinaMatriculaDao(em);
		m.getDisciplinas().forEach(disciplinaMatricula -> {
			disciplinaMatriculaDao.persist(disciplinaMatricula);
			disciplinasMatriculas.add(disciplinaMatricula);
		});
		m.setDisciplinas(disciplinasMatriculas);
		//
		InvestimentoDao investimentoDao = new InvestimentoDao(em);
		Investimento investimento = m.getInvestimento();
		investimentoDao.persist(investimento);
		m.setInvestimento(investimento);
		//
		SituacaoDoAlunoNaTurmaDao situacaoDoAlunoNaTurmaDao = new SituacaoDoAlunoNaTurmaDao(em);
		SituacaoDoAlunoNaTurma situacaoDoAlunoNaTurma = m.getSituacaoDoAlunoNaTurma();
		situacaoDoAlunoNaTurmaDao.persist(situacaoDoAlunoNaTurma);
		m.setSituacaoDoAlunoNaTurma(situacaoDoAlunoNaTurma);
		//
		SituacaoDoContratoDao situacaoDoContratoDao = new SituacaoDoContratoDao(em);
		SituacaoDoContrato situacaoDoContrato = m.getSituacaoDoContrato();
		situacaoDoContratoDao.persist(situacaoDoContrato);
		m.setSituacaoDoContrato(situacaoDoContrato);
		//
		TipoDeContratoDao tipoDeContratoDao = new TipoDeContratoDao(em);
		TipoDeContrato tipoDeContrato = m.getTipoDeContrato();
		tipoDeContratoDao.persist(tipoDeContrato);
		m.setTipoDeContrato(tipoDeContrato);
		//
		dao.persist(m);
		return m;
	}

}
