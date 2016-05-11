package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumMediaNotasParciais;
import br.com.cdan.model.pedagogico.NotasParciais;
import br.com.cdan.model.pedagogico.SistemaDeAvaliacao;
import br.com.cdan.negocio.pedagogico.NotasParciaisDao;
import br.com.cdan.negocio.pedagogico.SistemaDeAvaliacaoDao;

public class NotasParciaisFabricaTest {
	private static NotasParciaisFabricaTest instance = null;

	public static synchronized NotasParciaisFabricaTest getInstance() {
		if (instance == null) {
			instance = new NotasParciaisFabricaTest();
		}
		return instance;
	}

	public NotasParciais criaNotasParciais() {
		NotasParciais n = new NotasParciais();
		n.setAtivo(Boolean.TRUE);
		n.setDesconsideraAvaliacao(Boolean.FALSE);
		n.setEnumMediaNotasParciais(EnumMediaNotasParciais.MEDIAA);
		n.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacao());
		n.setUtilizaAgrupamentoDeAvaliacoes(Boolean.FALSE);
		n.setUtilizaRecuperacaoParcial(Boolean.FALSE);
		return n;
	}

	public NotasParciais criaNotasParciaisPersistido(EntityManager em) {
		NotasParciais n = criaNotasParciais();
		NotasParciaisDao dao = new NotasParciaisDao(em);
		//
		SistemaDeAvaliacaoDao sistemaDeAvaliacaoDao = new SistemaDeAvaliacaoDao(em);
		SistemaDeAvaliacao sistemaDeAvaliacao = n.getSistemaDeAvaliacao();
		sistemaDeAvaliacaoDao.persist(sistemaDeAvaliacao);
		n.setSistemaDeAvaliacao(sistemaDeAvaliacao);
		//
		dao.persist(sistemaDeAvaliacao);
		return n;
	}

}
