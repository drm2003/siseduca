package br.com.cdan.negocio.contato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.TipoDeAgendamento;
import br.com.cdan.negocio.contato.TipoDeAgendamentoDao;

public class TipoDeAgendamentoFabricaTest {
	private static TipoDeAgendamentoFabricaTest instance = null;

	public static synchronized TipoDeAgendamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeAgendamentoFabricaTest();
		}
		return instance;
	}

	public TipoDeAgendamento criaTipoDeAgendamento() {
		TipoDeAgendamento t = new TipoDeAgendamento();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao" + Math.random() * 10000);
		t.setCor("cor");
		return t;
	}

	public TipoDeAgendamento criaTipoDeAgendamentoPersistido(EntityManager em) {
		TipoDeAgendamento t = criaTipoDeAgendamento();
		TipoDeAgendamentoDao dao = new TipoDeAgendamentoDao(em);
		dao.persist(t);
		return t;
	}
}
