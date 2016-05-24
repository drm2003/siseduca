package br.com.cdan.negocio.pedagogico.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.DescricaoDeHabilidadesDao;

public class DescricaoDeHabilidadesFabricaTest extends FabricaTest {
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
		d.setDescricao(criarStringDinamicaPorTamanho(100));
		return d;
	}

	public DescricaoDeHabilidades criaDescricaoDeHabilidadesPersistido(EntityManager em) {
		DescricaoDeHabilidadesDao dao = new DescricaoDeHabilidadesDao(em);
		DescricaoDeHabilidades d = criaDescricaoDeHabilidades();
		dao.persist(d);
		return d;
	}

}
