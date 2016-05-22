package br.com.cdan.negocio.pedagogico.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.geral.SituacaoDoContratoDao;

public class SituacaoDoContratoFabricaTest extends FabricaTest {
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
		s.setDescricao(criarStringDinamicaPorTamanho(100));
		return s;
	}

	public SituacaoDoContrato criaSituacaoDoContratoPersistido(EntityManager em) {
		SituacaoDoContrato s = criaSituacaoDoContrato();
		SituacaoDoContratoDao dao = new SituacaoDoContratoDao(em);
		dao.persist(s);
		return s;
	}
}
