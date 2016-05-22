package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Utilizacao;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.UtilizacaoDao;

public class UtilizacaoFabricaTest extends FabricaTest{
	private static UtilizacaoFabricaTest instance = null;

	public static synchronized UtilizacaoFabricaTest getInstance() {
		if (instance == null) {
			instance = new UtilizacaoFabricaTest();
		}
		return instance;
	}

	public Utilizacao criaUtilizacao(EntityManager em) {
		Utilizacao u = new Utilizacao();
		u.setAtivo(Boolean.TRUE);
		u.setDescricao(criarStringDinamicaPorTamanho(100));
		u.setEquipamento(EquipamentoFabricaTest.getInstance().criaEquipamentoPersistido(em));
		return u;
	}

	public Utilizacao criaUtilizacaoPersistido(EntityManager em) {
		Utilizacao u = criaUtilizacao(em);
		UtilizacaoDao dao = new UtilizacaoDao(em);
		//
		dao.persist(u);
		return u;
	}

}
