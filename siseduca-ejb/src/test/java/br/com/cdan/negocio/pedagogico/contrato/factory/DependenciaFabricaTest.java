package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Dependencia;
import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.pedagogico.contrato.DependenciaDao;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.DisciplinaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

public class DependenciaFabricaTest {
	private static DependenciaFabricaTest instance = null;

	public static synchronized DependenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DependenciaFabricaTest();
		}
		return instance;
	}

	public Dependencia criaDependencia() {
		Dependencia d = new Dependencia();
		d.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		d.setAtivo(Boolean.TRUE);
		// Disciplinas
		Set<Disciplina> disciplinas = new LinkedHashSet<>();
		disciplinas.add(DisciplinaFabricaTest.getInstance().criaDisciplina());
		disciplinas.add(DisciplinaFabricaTest.getInstance().criaDisciplina());
		d.setDisciplinas(disciplinas);
		//
		return d;
	}

	public Dependencia criaDependenciaPersistido(EntityManager em) {
		Dependencia d = criaDependencia();
		DependenciaDao dependenciaDao = new DependenciaDao(em);
		// Aluno
		Aluno aluno = d.getAluno();
		AlunoDao alunoDao = new AlunoDao(em);
		alunoDao.persist(aluno);
		d.setAluno(aluno);
		// Disciplinas
		Set<Disciplina> disciplinas = new LinkedHashSet<>();
		DisciplinaDao disciplinaDao = new DisciplinaDao(em);
		d.getDisciplinas().forEach(disciplina -> {
			disciplinaDao.persist(disciplina);
			disciplinas.add(disciplina);
		});
		d.setDisciplinas(disciplinas);
		//
		dependenciaDao.persist(d);
		return d;
	}
}
