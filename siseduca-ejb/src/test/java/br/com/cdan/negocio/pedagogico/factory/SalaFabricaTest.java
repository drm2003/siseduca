package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Sala;
import br.com.cdan.negocio.pedagogico.SalaDao;

public class SalaFabricaTest {
	private static SalaFabricaTest instance = null;

	public static synchronized SalaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SalaFabricaTest();
		}
		return instance;
	}

	public Sala criaSala(EntityManager em) {
		Sala s = new Sala();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		s.setLocacao(Boolean.TRUE);
		s.setSigla("sigla");
		//
		s.setTipoDeSala(TipoDeSalaFabricaTest.getInstance().criaTipoDeSalaPersistido(em));
		s.setVagas(Long.valueOf(100));
		return s;
	}

	public Sala criaSalaPersistido(EntityManager em) {
		Sala s = criaSala(em);
		SalaDao dao = new SalaDao(em);
		//
		dao.persist(s);
		return s;
	}
}
