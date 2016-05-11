package br.com.cdan.negocio.geral.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Equipamento;
import br.com.cdan.model.geral.Utilizacao;
import br.com.cdan.model.pedagogico.Sala;
import br.com.cdan.negocio.geral.EquipamentoDao;
import br.com.cdan.negocio.geral.UtilizacaoDao;
import br.com.cdan.negocio.pedagogico.SalaDao;
import br.com.cdan.negocio.pedagogico.factory.SalaFabricaTest;

public class EquipamentoFabricaTest {
	private static EquipamentoFabricaTest instance = null;

	public static synchronized EquipamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EquipamentoFabricaTest();
		}
		return instance;
	}

	public Equipamento criaEquipamento() {
		Equipamento e = new Equipamento();
		e.setAtivo(Boolean.TRUE);
		e.setDataAquisicao(Calendar.getInstance());
		e.setDescricao("descricao");
		e.setNome("nome");
		e.setSala(SalaFabricaTest.getInstance().criaSala());
		//
		Set<Utilizacao> utilizacoes = new LinkedHashSet<>();
		utilizacoes.add(UtilizacaoFabricaTest.getInstance().criaUtilizacao());
		utilizacoes.add(UtilizacaoFabricaTest.getInstance().criaUtilizacao());
		e.setUtilizacoes(utilizacoes);
		return e;
	}

	public Equipamento criaEquipamentoPersistido(EntityManager em) {
		Equipamento e = criaEquipamento();
		EquipamentoDao dao = new EquipamentoDao(em);
		//
		SalaDao salaDao = new SalaDao(em);
		Sala sala = e.getSala();
		salaDao.persist(sala);
		e.setSala(sala);
		//
		UtilizacaoDao utilizacaoDao = new UtilizacaoDao(em);
		Set<Utilizacao> utilizacoes = new LinkedHashSet<>();
		e.getUtilizacoes().forEach(utilizacao -> {
			utilizacaoDao.persist(utilizacao);
			utilizacoes.add(utilizacao);
		});
		e.setUtilizacoes(utilizacoes);
		//
		dao.persist(e);
		return e;
	}
}
