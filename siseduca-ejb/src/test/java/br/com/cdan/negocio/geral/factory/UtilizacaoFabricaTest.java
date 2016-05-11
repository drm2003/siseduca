package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Equipamento;
import br.com.cdan.model.geral.Utilizacao;
import br.com.cdan.negocio.geral.EquipamentoDao;
import br.com.cdan.negocio.geral.UtilizacaoDao;

public class UtilizacaoFabricaTest {
	private static UtilizacaoFabricaTest instance = null;

	public static synchronized UtilizacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new UtilizacaoFabricaTest();
		}
		return instance;
	}

	public Utilizacao criaUtilizacao() {
		Utilizacao u = new Utilizacao();
		u.setAtivo(Boolean.TRUE);
		u.setDescricao("descricao");
		u.setEquipamento(EquipamentoFabricaTest.getInstance().criaEquipamento());
		return u;
	}

	public Utilizacao criaUtilizacaoPersistido(EntityManager em) {
		Utilizacao u = criaUtilizacao();
		UtilizacaoDao dao = new UtilizacaoDao(em);
		//
		EquipamentoDao equipamentoDao = new EquipamentoDao(em);
		Equipamento equipamento = u.getEquipamento();
		equipamentoDao.persist(equipamento);
		u.setEquipamento(equipamento);
		//
		dao.persist(u);
		return u;
	}

}
