package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Escolaridade;
import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.negocio.pedagogico.EscolaridadeDao;
import br.com.cdan.negocio.pedagogico.SeriePadraoDao;
import br.com.cdan.negocio.pedagogico.TipoDeCursoDao;

public class SeriePadraoFabricaTest {
	private static SeriePadraoFabricaTest instance = null;

	public static synchronized SeriePadraoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SeriePadraoFabricaTest();
		}
		return instance;
	}

	public SeriePadrao criaSeriePadrao() {
		SeriePadrao s = new SeriePadrao();
		s.setAtivo(Boolean.TRUE);
		s.setCodigo("codigo");
		s.setDescricao("descricao");
		s.setEscolaridade(EscolaridadeFabricaTest.getInstance().criaEscolaridade());
		s.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCurso());
		return s;
	}

	public SeriePadrao criaSeriePadraoPersistido(EntityManager em) {
		SeriePadrao s = criaSeriePadrao();
		SeriePadraoDao dao = new SeriePadraoDao(em);
		//
		EscolaridadeDao escolaridadeDao = new EscolaridadeDao(em);
		Escolaridade escolaridade = s.getEscolaridade();
		escolaridadeDao.persist(escolaridade);
		s.setEscolaridade(escolaridade);
		//
		TipoDeCursoDao tipoDeCursoDao = new TipoDeCursoDao(em);
		TipoDeCurso tipoDeCurso = s.getTipoDeCurso();
		tipoDeCursoDao.persist(tipoDeCurso);
		s.setTipoDeCurso(tipoDeCurso);
		//
		dao.persist(s);
		return s;
	}
}