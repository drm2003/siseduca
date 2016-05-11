package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.contrato.MotivoDeNaoFechamentoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeNaoFechamentoDeContratoDao;

public class MotivoDeNaoFechamentoDeContratoFabricaTest {
	private static MotivoDeNaoFechamentoDeContratoFabricaTest instance = null;

	public static synchronized MotivoDeNaoFechamentoDeContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MotivoDeNaoFechamentoDeContratoFabricaTest();
		}
		return instance;
	}

	public MotivoDeNaoFechamentoDeContrato criaMotivosDeNaoFechamentoDeContrato() {
		MotivoDeNaoFechamentoDeContrato m = new MotivoDeNaoFechamentoDeContrato();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		return m;
	}

	public MotivoDeNaoFechamentoDeContrato criaMotivosDeNaoFechamentoDeContratoPersistido(EntityManager em) {
		MotivoDeNaoFechamentoDeContrato m = criaMotivosDeNaoFechamentoDeContrato();
		MotivoDeNaoFechamentoDeContratoDao dao = new MotivoDeNaoFechamentoDeContratoDao(em);
		dao.persist(m);
		return m;
	}
}
