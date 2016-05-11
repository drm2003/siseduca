package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeCancelamentoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeCancelamentoDeContratoDao;

public class MotivoDeCancelamentoDeContratoFabricaTest {
	private static MotivoDeCancelamentoDeContratoFabricaTest instance = null;

	public static synchronized MotivoDeCancelamentoDeContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MotivoDeCancelamentoDeContratoFabricaTest();
		}
		return instance;
	}

	public MotivoDeCancelamentoDeContrato criaMotivoDeCancelamentoDeContrato() {
		MotivoDeCancelamentoDeContrato m = new MotivoDeCancelamentoDeContrato();
		m.setAtivo(Boolean.TRUE);
		m.setNome("nome");
		//
		return m;
	}

	public MotivoDeCancelamentoDeContrato criaMotivoDeCancelamentoDeContratoPersistido(EntityManager em) {
		MotivoDeCancelamentoDeContrato m = criaMotivoDeCancelamentoDeContrato();
		MotivoDeCancelamentoDeContratoDao dao = new MotivoDeCancelamentoDeContratoDao(em);
		//
		dao.persist(m);
		return m;
	}

}
