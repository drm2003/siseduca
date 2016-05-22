package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.factory.SituacaoDoAlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.factory.Turma_DisciplinaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;

public class AlunoFabricaTest extends FabricaTest {
	private static AlunoFabricaTest instance = null;

	public static synchronized AlunoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AlunoFabricaTest();
		}
		return instance;
	}

	public Aluno criaAluno(EntityManager em) {
		Aluno aluno = new Aluno();
		aluno.setAtivo(Boolean.TRUE);
		aluno.setCodigoDeBarras("codigoDeBarras");
		aluno.setNumeroMatricula(criarStringDinamicaPorTamanho(10));
		//
		aluno.setInteressado(InteressadoFabricaTest.getInstance().criaInteressadoPersistido(em));
		aluno.setLoginPortal("loginPortal");
		aluno.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		aluno.setRa("ra");
		aluno.setReceberEmail(Boolean.TRUE);
		aluno.setReceberSMS(Boolean.TRUE);
		//
		aluno.setSenha("senha");
		aluno.setSituacaoDoAluno(SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAlunoPersistido(em));
		aluno.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_DisciplinaPersistido(em));
		return aluno;
	}

	public Aluno criaAlunoPersistido(EntityManager em) {
		AlunoDao dao = new AlunoDao(em);
		Aluno aluno = criaAluno(em);
		//
		dao.persist(aluno);
		return aluno;
	}
}
