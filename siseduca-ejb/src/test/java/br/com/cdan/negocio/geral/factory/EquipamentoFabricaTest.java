package br.com.cdan.negocio.geral.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Equipamento;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.EquipamentoDao;
import br.com.cdan.negocio.pedagogico.factory.SalaFabricaTest;

public class EquipamentoFabricaTest extends FabricaTest {
	private static EquipamentoFabricaTest instance = null;

	public static synchronized EquipamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EquipamentoFabricaTest();
		}
		return instance;
	}

	public Equipamento criaEquipamento(EntityManager em) {
		Equipamento e = new Equipamento();
		e.setAtivo(Boolean.TRUE);
		e.setDataAquisicao(Calendar.getInstance());
		e.setDescricao(criarStringDinamicaPorTamanho(100));
		e.setNome(criarStringDinamicaPorTamanho(100));
		e.setSala(SalaFabricaTest.getInstance().criaSalaPersistido(em));
		return e;
	}

	public Equipamento criaEquipamentoPersistido(EntityManager em) {
		Equipamento e = criaEquipamento(em);
		EquipamentoDao dao = new EquipamentoDao(em);
		//
		dao.persist(e);
		return e;
	}
}
