package br.com.cdan.negocio.pedagogico.contrato.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.TipoDeContratoDao;

public class TipoDeContratoFabricaTest extends FabricaTest {
	private static TipoDeContratoFabricaTest instance = null;

	public static synchronized TipoDeContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeContratoFabricaTest();
		}
		return instance;
	}

	public TipoDeContrato criaTipoDeContrato(EntityManager em) {
		TipoDeContrato t = new TipoDeContrato();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao(criarStringDinamicaPorTamanho(100));
		return t;
	}

	public TipoDeContrato criaTipoDeContratoPersistido(EntityManager em) {
		TipoDeContrato t = criaTipoDeContrato(em);
		TipoDeContratoDao dao = new TipoDeContratoDao(em);
		//
		dao.persist(t);
		return t;
	}
}
