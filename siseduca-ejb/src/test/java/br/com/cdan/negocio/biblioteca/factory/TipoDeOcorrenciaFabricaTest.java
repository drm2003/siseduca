package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.TipoDeOcorrencia;
import br.com.cdan.negocio.biblioteca.TipoDeOcorrenciaDao;

public class TipoDeOcorrenciaFabricaTest {
	private static TipoDeOcorrenciaFabricaTest instance = null;

	public static synchronized TipoDeOcorrenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeOcorrenciaFabricaTest();
		}
		return instance;
	}

	public TipoDeOcorrencia criaTipoDeOcorrencia() {
		TipoDeOcorrencia t = new TipoDeOcorrencia();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		return t;
	}

	public TipoDeOcorrencia criaTipoDeOcorrenciaPersistido(EntityManager em) {
		TipoDeOcorrencia t = criaTipoDeOcorrencia();
		TipoDeOcorrenciaDao dao = new TipoDeOcorrenciaDao(em);
		//
		dao.persist(t);
		return t;
	}
}
