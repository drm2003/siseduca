package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.AreasDeConhecimento;
import br.com.cdan.negocio.biblioteca.AreasDeConhecimentoDao;

public class AreasDeConhecimentoFabricaTest {
	private static AreasDeConhecimentoFabricaTest instance = null;

	public static synchronized AreasDeConhecimentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AreasDeConhecimentoFabricaTest();
		}
		return instance;
	}

	public AreasDeConhecimento criaAreasDeConhecimento() {
		AreasDeConhecimento a = new AreasDeConhecimento();
		a.setAtivo(Boolean.TRUE);
		a.setCompartilhado(true);
		a.setDescricao("Linguistica, Letras e Artes nº " +Math.random()*1000);
		return a;
	}

	public AreasDeConhecimento criaAreasDeConhecimentoPersistido(EntityManager em) {
		AreasDeConhecimentoDao dao = new AreasDeConhecimentoDao();
		dao.setEntityManager(em);
		//
		AreasDeConhecimento a = criaAreasDeConhecimento();
		dao.persist(a);
		return a;
	}

}