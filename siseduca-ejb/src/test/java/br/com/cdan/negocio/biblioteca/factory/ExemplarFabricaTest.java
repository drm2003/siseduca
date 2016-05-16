package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Exemplar;
import br.com.cdan.negocio.biblioteca.ExemplarDao;

public class ExemplarFabricaTest {
	private static ExemplarFabricaTest instance;

	public static ExemplarFabricaTest getInstance() {
		if (instance == null) {
			instance = new ExemplarFabricaTest();
		}
		return instance;
	}

	public Exemplar criaExemplar(EntityManager em) {
		Exemplar e = new Exemplar();
		e.setAno(Long.valueOf("2016"));
		e.setAtivo(Boolean.TRUE);
		e.setDadosDoExemplar(DadosDoExemplarFabricaTest.getInstance().criaDadosDoExemplar());
		e.setDataAquisicao(Calendar.getInstance());
		e.setDescricao("descricao " + Math.random() * 10000);
		e.setEdicao(Long.valueOf(10));
		e.setNumeroDoExemplar(Long.valueOf(10));
		e.setVolume(Long.valueOf(5));
		e.setObra(ObraFabricaTest.getInstance().criaObraPersistido(em));
		return e;
	}

	public Exemplar criaExemplarPersistido(EntityManager em) {
		ExemplarDao dao = new ExemplarDao(em);
		Exemplar e = criaExemplar(em);
		//
		dao.persist(e);
		return e;
	}
}
