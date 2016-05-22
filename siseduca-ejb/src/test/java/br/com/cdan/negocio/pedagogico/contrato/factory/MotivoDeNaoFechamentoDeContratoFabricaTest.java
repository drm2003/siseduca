package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeNaoFechamentoDeContrato;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeNaoFechamentoDeContratoDao;

public class MotivoDeNaoFechamentoDeContratoFabricaTest extends FabricaTest {
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
		m.setDescricao(criarStringDinamicaPorTamanho(30));
		return m;
	}

	public MotivoDeNaoFechamentoDeContrato criaMotivosDeNaoFechamentoDeContratoPersistido(EntityManager em) {
		MotivoDeNaoFechamentoDeContrato m = criaMotivosDeNaoFechamentoDeContrato();
		MotivoDeNaoFechamentoDeContratoDao dao = new MotivoDeNaoFechamentoDeContratoDao(em);
		dao.persist(m);
		return m;
	}
}
