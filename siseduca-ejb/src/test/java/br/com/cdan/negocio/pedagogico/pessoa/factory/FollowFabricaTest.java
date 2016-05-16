package br.com.cdan.negocio.pedagogico.pessoa.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.negocio.pedagogico.pessoa.FollowDao;

public class FollowFabricaTest {
	private static FollowFabricaTest instance = null;

	public static synchronized FollowFabricaTest getInstance() {
		if (instance == null) {
			instance = new FollowFabricaTest();
		}
		return instance;
	}

	public Follow criaFollow(EntityManager em) {
		Follow f = new Follow();
		f.setAtivo(Boolean.TRUE);
		f.setAlunoInteressado(AlunoInteressadoFabricaTest.getInstance().criaAlunoInteressadoPersistido(em));
		f.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		f.setInteressado(InteressadoFabricaTest.getInstance().criaInteressadoPersistido(em));
		f.setResponsavel(ResponsavelFabricaTest.getInstance().criaResponsavelPersistido(em));
		return f;
	}

	public Follow criaFollowPersistido(EntityManager em) {
		Follow f = criaFollow(em);
		FollowDao dao = new FollowDao(em);
		//
		dao.persist(f);
		return f;
	}
}
