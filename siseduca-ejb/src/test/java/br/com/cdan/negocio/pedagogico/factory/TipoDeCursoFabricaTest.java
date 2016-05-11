package br.com.cdan.negocio.pedagogico.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.model.geral.TipoDeServico;
import br.com.cdan.model.pedagogico.SeriePadrao;
import br.com.cdan.model.pedagogico.TipoDeCurso;
import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.negocio.geral.factory.TipoDeServicoFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.factory.CursoFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.factory.DisciplinaFabricaTest;

public class TipoDeCursoFabricaTest {
	private static TipoDeCursoFabricaTest instance = null;

	public static synchronized TipoDeCursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeCursoFabricaTest();
		}
		return instance;
	}

	public TipoDeCurso criaTipoDeCurso() {
		TipoDeCurso t = new TipoDeCurso();
		t.setAtivo(Boolean.TRUE);
		t.setCompartilhado(Boolean.TRUE);
		t.setCurso(CursoFabricaTest.getInstance().criaCurso());
		t.setDescricao("descricao");
		// Disciplinas
		Set<Disciplina> disciplinas = new LinkedHashSet<>();
		disciplinas.add(DisciplinaFabricaTest.getInstance().criaDisciplina());
		disciplinas.add(DisciplinaFabricaTest.getInstance().criaDisciplina());
		t.setDisciplina(disciplinas);
		//
		t.setReconhecidoPeloMec(Boolean.TRUE);
		// Séries Padrão
		Set<SeriePadrao> seriesPadrao = new LinkedHashSet<>();
		seriesPadrao.add(SeriePadraoFabricaTest.getInstance().criaSeriePadrao());
		seriesPadrao.add(SeriePadraoFabricaTest.getInstance().criaSeriePadrao());
		t.setSeriesPadrao(seriesPadrao);
		t.setTemMatrizCurricular(Boolean.TRUE);
		// Tipos de serviço
		Set<TipoDeServico> tiposDeServico = new LinkedHashSet<>();
		tiposDeServico.add(TipoDeServicoFabricaTest.getInstance().criaTipoDeServico());
		tiposDeServico.add(TipoDeServicoFabricaTest.getInstance().criaTipoDeServico());
		t.setTipoDeServico(tiposDeServico);
		//
		return t;
	}

}
