package br.com.cdan.negocio.pedagogico.diario.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.diario.DiarioDeAula;
import br.com.cdan.negocio.pedagogico.curso.factory.Turma_DisciplinaFabricaTest;
import br.com.cdan.negocio.pedagogico.diario.DiarioDeAulaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class DiarioDeAulaFabricaTest {
	private static DiarioDeAulaFabricaTest instance = null;

	public static synchronized DiarioDeAulaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DiarioDeAulaFabricaTest();
		}
		return instance;
	}

	public DiarioDeAula criaDiarioDeAula(EntityManager em) {
		DiarioDeAula d = new DiarioDeAula();
		//
		d.setAtivo(Boolean.TRUE);
		d.setControleDeConteudo(ControleDeConteudoFabricaTest.getInstance().criaControleDeConteudoPersistido(em));
		d.setControleDeFrequencia(ControleDeFrequenciaFabricaTest.getInstance().criaControleDeFrequenciaPersistido(em));
		//
		d.setProfessor(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		d.setTurma_Disciplina(Turma_DisciplinaFabricaTest.getInstance().criaTurma_DisciplinaPersistido(em));
		return d;
	}

	public DiarioDeAula criaDiarioDeAulaPersistido(EntityManager em) {
		DiarioDeAulaDao dao = new DiarioDeAulaDao(em);
		DiarioDeAula d = criaDiarioDeAula(em);
		dao.persist(d);
		return d;
	}
}