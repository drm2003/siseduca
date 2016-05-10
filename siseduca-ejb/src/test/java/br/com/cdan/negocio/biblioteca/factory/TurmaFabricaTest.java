package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.ContaAReceber;
import br.com.cdan.model.pedagogico.HorarioDeAula;
import br.com.cdan.model.pedagogico.SituacaoDaTurma;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.biblioteca.ContaAReceberDao;
import br.com.cdan.negocio.biblioteca.CursoDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.HorarioDeAulaDao;
import br.com.cdan.negocio.biblioteca.MatrizCurricularDao;
import br.com.cdan.negocio.biblioteca.OcorrenciaDao;
import br.com.cdan.negocio.biblioteca.SituacaoDaTurmaDao;
import br.com.cdan.negocio.biblioteca.TurmaDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;

public class TurmaFabricaTest {
	private static TurmaFabricaTest instance = null;

	public static synchronized TurmaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TurmaFabricaTest();
		}
		return instance;
	}

	public Turma criaTurma() {
		Turma t = new Turma();
		t.setAtivo(Boolean.TRUE);
		t.setCalendarioPadrao(CalendarioLetivoFabricaTest.getInstance().criaCalendarioLetivo());
		t.setCodigo(Long.valueOf("10"));
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		contasAReceber.add(ContaAReceberFabricaTest.getInstance().criaContaAReceber());
		t.setContasAReceber(contasAReceber);
		t.setCurso(CursoFabricaTest.getInstance().criaCurso());
		t.setDataInicio(Calendar.getInstance());
		t.setDataTermino(Calendar.getInstance());
		t.setHorarioDeAula(HorarioDeAulaFabricaTest.getInstance().criaHorarioDeAula());
		t.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricular());
		t.setNome("nome");
		// Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		ocorrencias.add(OcorrenciaFabricaTest.getInstance().criaOcorrencia());
		t.setOcorrencias(ocorrencias);
		//
		t.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		t.setSalaDeAulaPadrao("salaDeAulaPadrao");
		t.setSigla("sigla");
		t.setSituacaoDaTurma(SituacaoDaTurmaFabricaTest.getInstance().criaSituacaoDaTurma());
		//
		Set<Turma_Disciplina> turmas_Disciplina = new LinkedHashSet<>();
		turmas_Disciplina.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		turmas_Disciplina.add(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		t.setTurmas_Disciplinas(turmas_Disciplina);
		return t;
	}

	public Turma criaTurmaPersistido(EntityManager em) {
		Turma t = criaTurma();
		TurmaDao dao = new TurmaDao(em);
		// Contas a receber
		Set<ContaAReceber> contasAReceber = new LinkedHashSet<>();
		ContaAReceberDao contaAReceberDao = new ContaAReceberDao(em);
		t.getContasAReceber().forEach(contaAReceber -> {
			contaAReceberDao.persist(contaAReceber);
			contasAReceber.add(contaAReceber);
		});
		t.setContasAReceber(contasAReceber);
		//
		CursoDao cursoDao = new CursoDao(em);
		Curso curso = t.getCurso();
		cursoDao.persist(curso);
		t.setCurso(curso);
		//
		HorarioDeAulaDao horarioDeAulaDao = new HorarioDeAulaDao(em);
		HorarioDeAula horarioDeAula = t.getHorarioDeAula();
		horarioDeAulaDao.persist(horarioDeAula);
		t.setHorarioDeAula(horarioDeAula);
		//
		MatrizCurricularDao matrizCurricularDao = new MatrizCurricularDao(em);
		MatrizCurricular matrizCurricular = t.getMatrizCurricular();
		matrizCurricularDao.persist(matrizCurricular);
		t.setMatrizCurricular(matrizCurricular);
		// Ocorrências
		Set<Ocorrencia> ocorrencias = new LinkedHashSet<>();
		OcorrenciaDao ocorrenciaDao = new OcorrenciaDao(em);
		t.getOcorrencias().forEach(ocorrencia -> {
			ocorrenciaDao.persist(ocorrencia);
			ocorrencias.add(ocorrencia);
		});
		t.setOcorrencias(ocorrencias);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario professor = t.getProfessor();
		funcionarioDao.persist(professor);
		t.setProfessor(professor);
		//
		SituacaoDaTurmaDao situacaoDaTurmaDao = new SituacaoDaTurmaDao(em);
		SituacaoDaTurma situacaoDaTurma = t.getSituacaoDaTurma();
		situacaoDaTurmaDao.persist(situacaoDaTurma);
		t.setSituacaoDaTurma(situacaoDaTurma);
		//
		Set<Turma_Disciplina> turmas_Disciplinas = new LinkedHashSet<>();
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		t.getTurmas_Disciplinas().forEach(turma_disciplina -> {
			turma_DisciplinaDao.persist(turma_disciplina);
			turmas_Disciplinas.add(turma_disciplina);
		});
		t.setTurmas_Disciplinas(turmas_Disciplinas);
		//
		dao.persist(t);
		return t;
	}
}
