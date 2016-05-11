package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.pedagogico.DescricaoDeHabilidades;
import br.com.cdan.model.pedagogico.GrupoDeHabilidades;

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
		//
		Set<GrupoDeHabilidades> grupoDeHabilidades = new LinkedHashSet<>();
		grupoDeHabilidades.add(GrupoDeHabilidadesFabricaTest.getInstance().criaGrupoDeHabilidades());
		grupoDeHabilidades.add(GrupoDeHabilidadesFabricaTest.getInstance().criaGrupoDeHabilidades());
		d.setGrupoDeHabilidades(grupoDeHabilidades);
		//
		return d;
	}

}
