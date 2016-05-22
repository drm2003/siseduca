package br.com.cdan.negocio.geral.cep.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.Cidade;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.CidadeDao;

public class CidadeFabricaTest extends FabricaTest {
	private static CidadeFabricaTest instance = null;

	public static synchronized CidadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new CidadeFabricaTest();
		}
		return instance;
	}

	public Cidade criaCidade(EntityManager em) {
		Cidade c = new Cidade();
		c.setAtivo(Boolean.TRUE);
		c.setDescricao(criarStringDinamicaPorTamanho(100));
		c.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUFPersistido(em));
		return c;
	}

	public Cidade criaCidadePersistido(EntityManager em) {
		CidadeDao dao = new CidadeDao(em);
		Cidade c = criaCidade(em);
		dao.persist(c);
		return c;
	}

}
