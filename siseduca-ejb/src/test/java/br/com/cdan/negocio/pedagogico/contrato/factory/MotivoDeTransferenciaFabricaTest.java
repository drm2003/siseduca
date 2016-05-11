package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeTransferenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.TransferenciaDao;

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
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		m.setTransferencias(transferencias);
		return m;
	}

	public MotivoDeTransferencia criaMotivoDeTransferenciaPersistido(EntityManager em) {
		MotivoDeTransferencia m = criaMotivoDeTransferencia();
		MotivoDeTransferenciaDao dao = new MotivoDeTransferenciaDao(em);
		//
		TransferenciaDao transferenciaDao = new TransferenciaDao(em);
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		m.getTransferencias().forEach(transferencia -> {
			transferenciaDao.persist(transferencia);
			transferencias.add(transferencia);
		});
		m.setTransferencias(transferencias);
		//
		dao.persist(m);
		return m;
	}
}
