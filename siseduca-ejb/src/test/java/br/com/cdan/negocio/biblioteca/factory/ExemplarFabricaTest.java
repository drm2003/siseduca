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

	public Exemplar criaExemplar() {
		Exemplar exemplar = new Exemplar();
		exemplar.setAno(Long.valueOf("2016"));
		exemplar.setAtivo(Boolean.TRUE);
		exemplar.setDadosDoExemplar(DadosDoExemplarFabricaTest.getInstance().criaDadosDoExemplar());
		exemplar.setDataAquisicao(Calendar.getInstance());
		exemplar.setDescricao("teste");
		exemplar.setEdicao(Long.valueOf(10));
		exemplar.setNumeroDoExemplar(Long.valueOf(10));
		exemplar.setObra(ObraFabricaTest.getInstance().criaObra());
		exemplar.setVolume(Long.valueOf(5));
		return null;
	}

	public Exemplar criaExemplarPersistido(EntityManager em) {
		ExemplarDao dao = new ExemplarDao();
		dao.setEntityManager(em);
		Exemplar exemplar = criaExemplar();
		return exemplar;
	}
}
