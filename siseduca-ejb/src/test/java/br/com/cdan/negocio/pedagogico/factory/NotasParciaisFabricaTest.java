package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumMediaNotasParciais;
import br.com.cdan.model.pedagogico.NotasParciais;
import br.com.cdan.negocio.pedagogico.NotasParciaisDao;

public class NotasParciaisFabricaTest {
	private static NotasParciaisFabricaTest instance = null;

	public static synchronized NotasParciaisFabricaTest getInstance() {
		if (instance == null) {
			instance = new NotasParciaisFabricaTest();
		}
		return instance;
	}

	public NotasParciais criaNotasParciais(EntityManager em) {
		NotasParciais n = new NotasParciais();
		n.setAtivo(Boolean.TRUE);
		n.setDesconsideraAvaliacao(Boolean.FALSE);
		n.setEnumMediaNotasParciais(EnumMediaNotasParciais.MEDIAA);
		n.setSistemaDeAvaliacao(SistemaDeAvaliacaoFabricaTest.getInstance().criaSistemaDeAvaliacaoPersistido(em));
		n.setUtilizaAgrupamentoDeAvaliacoes(Boolean.FALSE);
		n.setUtilizaRecuperacaoParcial(Boolean.FALSE);
		return n;
	}

	public NotasParciais criaNotasParciaisPersistido(EntityManager em) {
		NotasParciais n = criaNotasParciais(em);
		NotasParciaisDao dao = new NotasParciaisDao(em);
		dao.persist(n);
		return n;
	}

}
