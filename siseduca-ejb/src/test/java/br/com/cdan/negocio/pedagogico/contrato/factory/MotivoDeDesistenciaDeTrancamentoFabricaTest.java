package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.MotivoDeDesistenciaDeTrancamento;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeDesistenciaDeTrancamentoDao;

public class MotivoDeDesistenciaDeTrancamentoFabricaTest extends FabricaTest {
	private static MotivoDeDesistenciaDeTrancamentoFabricaTest instance = null;

	public static synchronized MotivoDeDesistenciaDeTrancamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new MotivoDeDesistenciaDeTrancamentoFabricaTest();
		}
		return instance;
	}

	public MotivoDeDesistenciaDeTrancamento criaMotivoDeDesistenciaDeTrancamento() {
		MotivoDeDesistenciaDeTrancamento m = new MotivoDeDesistenciaDeTrancamento();
		m.setAtivo(Boolean.TRUE);
		m.setDescricao(criarStringDinamicaPorTamanho(50));
		//
		return m;
	}

	public MotivoDeDesistenciaDeTrancamento criaMotivoDeDesistenciaDeTrancamentoPersistido(EntityManager em) {
		MotivoDeDesistenciaDeTrancamento m = criaMotivoDeDesistenciaDeTrancamento();
		MotivoDeDesistenciaDeTrancamentoDao dao = new MotivoDeDesistenciaDeTrancamentoDao(em);
		//
		dao.persist(m);
		return m;
	}
}