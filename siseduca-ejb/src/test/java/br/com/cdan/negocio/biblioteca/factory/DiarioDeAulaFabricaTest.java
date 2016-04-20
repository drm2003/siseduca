package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.contrato.DisciplinaMatricula;
import br.com.cdan.model.pedagogico.curso.DiaDaSemanaAula;
import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.model.pessoa.Aluno;

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
		d.setDisciplinaMatricula(disciplinaMatricula);
		//
		d.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionario());
		d.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_Disciplina());
		return d;
	}
}
