package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.Escolaridade;
import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.negocio.pedagogico.EscolaridadeDao;

public class EscolaridadeFabricaTest {
	private static EscolaridadeFabricaTest instance = null;

	public static synchronized EscolaridadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new EscolaridadeFabricaTest();
		}
		return instance;
	}

	public Escolaridade criaEscolaridade() {
		Escolaridade e = new Escolaridade();
		e.setDescricao("descricao");
		//
		Set<SeriePadrao> seriesPadrao = new LinkedHashSet<>();
		e.setSeriesPadrao(seriesPadrao);
		e.setAtivo(Boolean.TRUE);
		return e;
	}

	public Escolaridade criaEscolaridadePersistido(EntityManager em) {
		Escolaridade e = criaEscolaridade();
		EscolaridadeDao dao = new EscolaridadeDao(em);
		//
		e.setDescricao("descricao");
		//
		Set<SeriePadrao> seriesPadrao = new LinkedHashSet<>();
		e.setSeriesPadrao(seriesPadrao);
		//
		dao.persist(e);
		return e;
	}
}