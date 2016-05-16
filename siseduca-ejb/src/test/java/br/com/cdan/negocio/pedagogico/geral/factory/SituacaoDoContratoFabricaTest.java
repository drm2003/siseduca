package br.com.cdan.negocio.pedagogico.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;

public class SituacaoDoContratoFabricaTest {
	private static SituacaoDoContratoFabricaTest instance = null;

	public static synchronized SituacaoDoContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDoContratoFabricaTest();
		}
		return instance;
	}

	public SituacaoDoContrato criaSituacaoDoContrato() {
		SituacaoDoContrato s = new SituacaoDoContrato();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		return s;
	}

	public SituacaoDoContrato criaSituacaoDoContratoPersistido(EntityManager em) {
		SituacaoDoContrato s = new SituacaoDoContrato();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		return s;
	}
}
