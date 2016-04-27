package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.model.pedagogico.curso.Turma_Disciplina;
import br.com.cdan.model.pedagogico.diario.ControleDeConteudo;
import br.com.cdan.model.pedagogico.diario.ControleDeFrequencia;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.ControleDeConteudoDao;
import br.com.cdan.negocio.biblioteca.ControleDeFrequenciaDao;
import br.com.cdan.negocio.biblioteca.DiaDaSemanaAulaDao;
import br.com.cdan.negocio.biblioteca.DiarioDeAulaDao;
import br.com.cdan.negocio.biblioteca.DisciplinaMatriculaDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.Turma_DisciplinaDao;

public class DiarioDeAulaFabricaTest {
	private static DiarioDeAulaFabricaTest instance = null;

	public static synchronized DiarioDeAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DiarioDeAulaFabricaTest();
		}
		return instance;
	}

	public DiarioDeAula criaDiarioDeAula() {
		DiarioDeAula d = new DiarioDeAula();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		alunos.add(AlunoFabricaTest.getInstance().criaAluno());
		d.setAlunos(alunos);
		//
		d.setAtivo(Boolean.TRUE);
		d.setControleDeConteudo(ControleDeConteudoFabricaTest.getInstance().criaControleDeConteudo());
		d.setControleDeFrequencia(ControleDeFrequenciaFabricaTest.getInstance().criaControleDeFrequencia());
		// Dias da Semana de aula
		Set<DiaDaSemanaAula> diasDaSemanaAula = new LinkedHashSet<>();
		diasDaSemanaAula.add(DiaDaSemanaAulaFabricaTest.getInstance().criaDiaDaSemanaAula());
		diasDaSemanaAula.add(DiaDaSemanaAulaFabricaTest.getInstance().criaDiaDaSemanaAula());
		d.setDiasDaSemanaAula(diasDaSemanaAula);
		//
		Set<DisciplinaMatricula> disciplinaMatriculas = new LinkedHashSet<>();
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		disciplinaMatriculas.add(DisciplinaMatriculaFabricaTest.getInstance().criaDisciplinaMatricula());
		d.setDisciplinaMatricula(disciplinaMatriculas);
		//
		d.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		d.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		return d;
	}

	public DiarioDeAula criaDiarioDeAulaPersistido(EntityManager em) {
		DiarioDeAulaDao dao = new DiarioDeAulaDao(em);

		DiarioDeAula d = new DiarioDeAula();
		// Alunos
		Set<Aluno> alunos = new LinkedHashSet<>();
		AlunoDao alunoDao = new AlunoDao(em);
		d.getAlunos().forEach(aluno -> {
			alunoDao.persist(aluno);
			alunos.add(aluno);
		});
		d.setAlunos(alunos);
		//
		ControleDeConteudoDao controleDeConteudoDao = new ControleDeConteudoDao(em);
		ControleDeConteudo controleDeConteudo = d.getControleDeConteudo();
		controleDeConteudoDao.persist(controleDeConteudo);
		d.setControleDeConteudo(controleDeConteudo);
		//
		ControleDeFrequenciaDao controleDeFrequenciaDao = new ControleDeFrequenciaDao(em);
		ControleDeFrequencia controleDeFrequencia = new ControleDeFrequencia();
		controleDeFrequenciaDao.persist(controleDeFrequencia);
		d.setControleDeFrequencia(controleDeFrequencia);
		// Dias da Semana de aula
		Set<DiaDaSemanaAula> diasDaSemanaAula = new LinkedHashSet<>();
		DiaDaSemanaAulaDao diaDaSemanaAulaDao = new DiaDaSemanaAulaDao(em);
		d.getDiasDaSemanaAula().forEach(diaDaSemanaAula -> {
			diaDaSemanaAulaDao.persist(diaDaSemanaAula);
			diasDaSemanaAula.add(diaDaSemanaAula);
		});
		d.setDiasDaSemanaAula(diasDaSemanaAula);
		//
		Set<DisciplinaMatricula> disciplinaMatriculas = new LinkedHashSet<>();
		DisciplinaMatriculaDao disciplinaMatriculaDao = new DisciplinaMatriculaDao(em);
		d.getDisciplinaMatricula().forEach(disciplinaMatricula -> {
			disciplinaMatriculaDao.persist(disciplinaMatricula);
			disciplinaMatriculas.add(disciplinaMatricula);
		});
		d.setDisciplinaMatricula(disciplinaMatriculas);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario professor = d.getProfessor();
		funcionarioDao.persist(professor);
		d.setProfessor(professor);
		//
		Turma_DisciplinaDao turma_DisciplinaDao = new Turma_DisciplinaDao(em);
		Turma_Disciplina turma_Disciplina = d.getTurma_Disciplina();
		turma_DisciplinaDao.persist(turma_Disciplina);
		d.setTurma_Disciplina(turma_Disciplina);
		//
		dao.persist(d);
		return d;
	}
}