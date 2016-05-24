package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Turno;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.TurnoDao;

public class TurnoFabricaTest extends FabricaTest {
	private static TurnoFabricaTest instance = null;

	public static synchronized TurnoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TurnoFabricaTest();
		}
		return instance;
	}

	public Turno criaTurno() {
		Turno t = new Turno();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao(criarStringDinamicaPorTamanho(100));
		t.setCompartilhado(Boolean.TRUE);
		//
		return t;
	}

	public Turno criaTurnoPersistido(EntityManager em) {
		Turno t = criaTurno();
		TurnoDao dao = new TurnoDao(em);
		//
		dao.persist(t);
		return t;
	}
}
