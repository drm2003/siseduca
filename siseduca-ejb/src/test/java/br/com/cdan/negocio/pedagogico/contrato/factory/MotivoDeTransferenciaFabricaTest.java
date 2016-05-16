package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeTransferenciaDao;

public class MotivoDeTransferenciaFabricaTest {
	private static MotivoDeTransferenciaFabricaTest instance = null;

	public static synchronized MotivoDeTransferenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MotivoDeTransferenciaFabricaTest();
		}
		return instance;
	}

	public MotivoDeTransferencia criaMotivoDeTransferencia() {
		MotivoDeTransferencia m = new MotivoDeTransferencia();
		//
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		//
		return m;
	}

	public MotivoDeTransferencia criaMotivoDeTransferenciaPersistido(EntityManager em) {
		MotivoDeTransferencia m = criaMotivoDeTransferencia();
		MotivoDeTransferenciaDao dao = new MotivoDeTransferenciaDao(em);
		//
		dao.persist(m);
		return m;
	}
}
