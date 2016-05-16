package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;
import br.com.cdan.negocio.pedagogico.DescricaoDeHabilidadesDao;

public class DescricaoDeHabilidadesFabricaTest {
	private static DescricaoDeHabilidadesFabricaTest instance = null;

	public static synchronized DescricaoDeHabilidadesFabricaTest getInstance() {
		if (instance == null) {
			instance = new DescricaoDeHabilidadesFabricaTest();
		}
		return instance;
	}

	public DescricaoDeHabilidades criaDescricaoDeHabilidades() {
		DescricaoDeHabilidades d = new DescricaoDeHabilidades();
		d.setAtivo(Boolean.TRUE);
		d.setDescricao("descricao");
		return d;
	}

	public DescricaoDeHabilidades criaDescricaoDeHabilidadesPersistido(EntityManager em) {
		DescricaoDeHabilidades d = criaDescricaoDeHabilidades();
		DescricaoDeHabilidadesDao dao = new DescricaoDeHabilidadesDao(em);
		dao.persist(dao);
		return d;
	}

}
