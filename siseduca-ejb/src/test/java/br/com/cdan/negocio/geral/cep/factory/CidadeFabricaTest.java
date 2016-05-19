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
		Cidade cidade = new Cidade();
		cidade.setAtivo(Boolean.TRUE);
		cidade.setDescricao(criarStringDinamicaPorTamanho(100));
		cidade.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUFPersistido(em));
		return cidade;
	}

	public Cidade criaCidadePersistido(EntityManager em) {
		CidadeDao dao = new CidadeDao(em);
		Cidade cidade = criaCidade(em);
		dao.persist(cidade);
		return cidade;
	}

}
