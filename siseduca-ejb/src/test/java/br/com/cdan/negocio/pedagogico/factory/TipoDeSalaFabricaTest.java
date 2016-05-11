package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Sala;
import br.com.cdan.model.pedagogico.TipoDeSala;
import br.com.cdan.negocio.pedagogico.SalaDao;
import br.com.cdan.negocio.pedagogico.TipoDeSalaDao;

public class TipoDeSalaFabricaTest {
	private static TipoDeSalaFabricaTest instance = null;

	public static synchronized TipoDeSalaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeSalaFabricaTest();
		}
		return instance;
	}

	public TipoDeSala criaTipoDeSala() {
		TipoDeSala t = new TipoDeSala();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		Set<Sala> salas = new LinkedHashSet<>();
		salas.add(SalaFabricaTest.getInstance().criaSala());
		salas.add(SalaFabricaTest.getInstance().criaSala());
		//
		t.setSalas(salas);
		return t;
	}

	public TipoDeSala criaTipoDeSalaPersistido(EntityManager em) {
		TipoDeSala t = criaTipoDeSala();
		TipoDeSalaDao dao = new TipoDeSalaDao(em);
		//
		SalaDao salaDao = new SalaDao(em);
		Set<Sala> salas = new LinkedHashSet<>();
		t.getSalas().forEach(sala -> {
			salaDao.persist(sala);
			salas.add(sala);
		});
		t.setSalas(salas);
		//
		dao.persist(t);
		return t;
	}
}
