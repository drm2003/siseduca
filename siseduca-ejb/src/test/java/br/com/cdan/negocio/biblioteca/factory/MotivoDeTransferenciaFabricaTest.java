package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.negocio.biblioteca.MotivoDeTransferenciaDao;
import br.com.cdan.negocio.biblioteca.TransferenciaDao;

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
		m.setAtivo(Boolean.TRUE);
		m.setDescricao("descricao");
		//
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		m.setTranferencias(transferencias);
		//
		return m;
	}

	public MotivoDeTransferencia criaMotivoDeTransferenciaPersistido(EntityManager em) {
		MotivoDeTransferencia m = criaMotivoDeTransferencia();
		MotivoDeTransferenciaDao dao = new MotivoDeTransferenciaDao(em);
		//
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		TransferenciaDao transferenciaDao = new TransferenciaDao(em);
		m.getTranferencias().forEach(transferencia -> {
			transferenciaDao.persist(transferencia);
			transferencias.add(transferencia);
		});
		m.setTranferencias(transferencias);
		//
		dao.persist(m);
		return m;
	}

}
