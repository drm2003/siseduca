package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Equipamento;
import br.com.cdan.model.pedagogico.Sala;
import br.com.cdan.model.pedagogico.TipoDeSala;
import br.com.cdan.negocio.geral.EquipamentoDao;
import br.com.cdan.negocio.geral.factory.EquipamentoFabricaTest;
import br.com.cdan.negocio.pedagogico.SalaDao;
import br.com.cdan.negocio.pedagogico.TipoDeSalaDao;

public class SalaFabricaTest {
	private static SalaFabricaTest instance = null;

	public static synchronized SalaFabricaTest getInstance() {
		if (instance == null) {
			instance = new SalaFabricaTest();
		}
		return instance;
	}

	public Sala criaSala() {
		Sala s = new Sala();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		Set<Equipamento> equipamentos = new LinkedHashSet<>();
		equipamentos.add(EquipamentoFabricaTest.getInstance().criaEquipamento());
		equipamentos.add(EquipamentoFabricaTest.getInstance().criaEquipamento());
		s.setEquipamentos(equipamentos);
		//
		s.setLocacao(Boolean.TRUE);
		s.setSigla("sigla");
		//
		s.setTipoDeSala(TipoDeSalaFabricaTest.getInstance().criaTipoDeSala());
		s.setVagas(Long.valueOf(100));
		return s;
	}

	public Sala criaSalaPersistido(EntityManager em) {
		Sala s = criaSala();
		SalaDao dao = new SalaDao(em);
		//
		EquipamentoDao equipamentoDao = new EquipamentoDao(em);
		Set<Equipamento> equipamentos = new LinkedHashSet<>();
		s.getEquipamentos().forEach(equipamento -> {
			equipamentoDao.persist(equipamento);
			equipamentos.add(equipamento);
		});
		s.setEquipamentos(equipamentos);
		//
		TipoDeSalaDao tipoDeSalaDao = new TipoDeSalaDao(em);
		TipoDeSala tipoDeSala = s.getTipoDeSala();
		tipoDeSalaDao.persist(tipoDeSala);
		s.setTipoDeSala(tipoDeSala);
		//
		dao.persist(s);
		return s;
	}
}
