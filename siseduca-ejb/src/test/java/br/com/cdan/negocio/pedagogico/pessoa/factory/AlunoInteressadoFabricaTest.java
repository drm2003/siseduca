package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTurnoPretendido;
import br.com.cdan.model.pessoa.AlunoInteressado;
import br.com.cdan.negocio.geral.factory.SituacaoDoAlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoInteressadoDao;

public class AlunoInteressadoFabricaTest {
	private static AlunoInteressadoFabricaTest instance = null;

	public static synchronized AlunoInteressadoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AlunoInteressadoFabricaTest();
		}
		return instance;
	}

	public AlunoInteressado criaAlunoInteressado(EntityManager em) {
		AlunoInteressado a = new AlunoInteressado();
		a.setAtivo(Boolean.TRUE);
		//
		a.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado(em));
		a.setObservacoes("observacoes");
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoaPersistido(em));
		a.setReceberEmail(Boolean.TRUE);
		a.setReceberSMS(Boolean.TRUE);
		//
		a.setSituacaoDoAluno(SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAlunoPersistido(em));
		a.setTurnoPretendido(EnumTurnoPretendido.MANHA);
		return a;
	}

	public AlunoInteressado criaAlunoInteressadoPersistido(EntityManager em) {
		AlunoInteressado a = criaAlunoInteressado(em);
		AlunoInteressadoDao dao = new AlunoInteressadoDao(em);
		//
		dao.persist(a);
		return a;
	}
}
