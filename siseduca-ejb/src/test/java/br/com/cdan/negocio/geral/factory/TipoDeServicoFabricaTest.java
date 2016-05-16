package br.com.cdan.negocio.geral.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.negocio.geral.TipoDeServicoDao;

public class TipoDeServicoFabricaTest {
	private static TipoDeServicoFabricaTest instance = null;

	public static synchronized TipoDeServicoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeServicoFabricaTest();
		}
		return instance;
	}

	public TipoDeServico criaTipoDeServico(EntityManager em) {
		TipoDeServico t = new TipoDeServico();
		t.setAtivo(Boolean.TRUE);
		t.setCategoria(CategoriaFabricaTest.getInstance().criaCategoriaPersistido(em));
		t.setDescricao("descricao");
		t.setDias(Long.valueOf("10"));
		t.setPrimeiraSolicitacaoGratuita(Boolean.TRUE);
		t.setValor(BigDecimal.TEN);
		//
		return t;
	}

	public TipoDeServico criaTipoDeServicoPersistido(EntityManager em) {
		TipoDeServicoDao dao = new TipoDeServicoDao(em);
		TipoDeServico t = criaTipoDeServico(em);
		//
		dao.persist(t);
		return t;
	}
}
