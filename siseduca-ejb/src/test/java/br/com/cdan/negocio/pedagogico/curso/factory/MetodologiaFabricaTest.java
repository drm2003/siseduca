package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Metodologia;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.curso.MetodologiaDao;

public class MetodologiaFabricaTest extends FabricaTest {
	private static MetodologiaFabricaTest instance = null;

	public static synchronized MetodologiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new MetodologiaFabricaTest();
		}
		return instance;
	}

	public Metodologia criaMetodologia(EntityManager em) {
		Metodologia m = new Metodologia();
		m.setAtividadesPraticas(criarStringDinamicaPorTamanho(10));
		m.setAtivo(Boolean.TRUE);
		m.setBibliografiaBasica(criarStringDinamicaPorTamanho(10));
		m.setBibliografiaComplementar(criarStringDinamicaPorTamanho(10));
		m.setCriteriosDeAvaliacao(criarStringDinamicaPorTamanho(10));
		m.setRecursosDidaticos(criarStringDinamicaPorTamanho(10));
		m.setTecnicasDeEnsino(criarStringDinamicaPorTamanho(10));
		return m;
	}

	public Metodologia criaMetodologiaPersistido(EntityManager em) {
		Metodologia m = criaMetodologia(em);
		MetodologiaDao dao = new MetodologiaDao(em);
		//
		dao.persist(m);
		return m;
	}
}
