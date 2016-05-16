package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pessoa.CarteiraHabilitacao;
import br.com.cdan.negocio.geral.factory.EstadoUFFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.CarteiraHabilitacaoDao;

public class CarteiraHabilitacaoFabricaTest {
	private static CarteiraHabilitacaoFabricaTest instance = null;

	public static synchronized CarteiraHabilitacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CarteiraHabilitacaoFabricaTest();
		}
		return instance;
	}

	public CarteiraHabilitacao criaCarteiraHabilitacao(EntityManager em) {
		CarteiraHabilitacao c = new CarteiraHabilitacao();
		c.setAtivo(Boolean.TRUE);
		c.setCategoria("categoria");
		c.setEstado(EstadoUFFabricaTest.getInstance().criaEstadoUFPersistido(em));
		c.setNumeroCNH("numeroCNH");
		c.setNumeroRegistro("numeroRegistro");
		c.setOrgaoEmissor("orgaoEmissor");
		c.setValidade(Calendar.getInstance());
		return c;
	}

	public CarteiraHabilitacao criaCarteiraHabilitacaoPersistido(EntityManager em) {
		CarteiraHabilitacao c = criaCarteiraHabilitacao(em);
		CarteiraHabilitacaoDao dao = new CarteiraHabilitacaoDao(em);
		dao.persist(c);
		return c;
	}
}
