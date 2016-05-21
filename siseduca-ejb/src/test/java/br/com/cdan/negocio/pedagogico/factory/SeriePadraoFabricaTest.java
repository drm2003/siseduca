package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.SeriePadraoDao;

public class SeriePadraoFabricaTest extends FabricaTest {
	private static SeriePadraoFabricaTest instance = null;

	public static synchronized SeriePadraoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SeriePadraoFabricaTest();
		}
		return instance;
	}

	public SeriePadrao criaSeriePadrao(EntityManager em) {
		SeriePadrao s = new SeriePadrao();
		s.setAtivo(Boolean.TRUE);
		s.setCodigo("codigo");
		s.setDescricao(criarStringDinamicaPorTamanho(100));
		s.setEscolaridade(EscolaridadeFabricaTest.getInstance().criaEscolaridadePersistido(em));
		s.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCursoPersistido(em));
		return s;
	}

	public SeriePadrao criaSeriePadraoPersistido(EntityManager em) {
		SeriePadrao s = criaSeriePadrao(em);
		SeriePadraoDao dao = new SeriePadraoDao(em);
		//
		dao.persist(s);
		return s;
	}
}