package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.negocio.biblioteca.ObraDao;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;

public class ObraFabricaTest {
	private static ObraFabricaTest instance = null;

	public static synchronized ObraFabricaTest getInstance() {
		if (instance == null) {
			instance = new ObraFabricaTest();
		}
		return instance;
	}

	public Obra criaObra(EntityManager em) {
		Obra a = new Obra();
		a.setAssuntoPalavraChave("teste");
		a.setClassificacaoCDD("teste");
		a.setClassificacaoCutter("teste");
		//
		a.setEditora(EditoraFabricaTest.getInstance().criaEditoraPersistido(em));
		//
		a.setIdioma(IdiomaFabricaTest.getInstance().criaIdiomaPersistido(em));
		a.setNivel(NivelFabricaTest.getInstance().criaNivelPersistido(em));
		a.setNome("Teste " + Math.random() * 1000);
		a.setObservacoes("teste");
		a.setOrigem(OrigemFabricaTest.getInstance().criaOrigemPersistido(em));
		a.setSerieColecaoLiteral(SerieColecaoLiteralFabricaTest.getInstance().criaSerieColecaoLiteralPersistido(em));
		a.setSetor(SetorFabricaTest.getInstance().criaSetorPersistido(em));
		a.setSubtitulo("");
		a.setTipoDeObra(TipoDeObraFabricaTest.getInstance().criaTipoDeObraPersistido(em));
		a.setCompartilhado(Boolean.TRUE);
		a.setAtivo(Boolean.TRUE);
		return a;
	}

	public Obra criaObraPersistido(EntityManager em) {
		ObraDao dao = new ObraDao(em);
		Obra a = criaObra(em);
		// Gravando obra completa
		dao.persist(a);
		return a;
	}
}