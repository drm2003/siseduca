package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.ClassificacaoLiteraria;
import br.com.cdan.model.biblioteca.Exemplar;
import br.com.cdan.model.biblioteca.Idioma;
import br.com.cdan.model.biblioteca.Nivel;
import br.com.cdan.model.biblioteca.Obra;
import br.com.cdan.model.biblioteca.SerieColecaoLiteral;
import br.com.cdan.model.biblioteca.Setor;
import br.com.cdan.model.biblioteca.TipoDeObra;
import br.com.cdan.model.geral.Origem;
import br.com.cdan.negocio.biblioteca.ClassificacaoLiterariaDao;
import br.com.cdan.negocio.biblioteca.ExemplarDao;
import br.com.cdan.negocio.biblioteca.IdiomaDao;
import br.com.cdan.negocio.biblioteca.NivelDao;
import br.com.cdan.negocio.biblioteca.ObraDao;
import br.com.cdan.negocio.biblioteca.SerieColecaoLiteralDao;
import br.com.cdan.negocio.biblioteca.SetorDao;
import br.com.cdan.negocio.biblioteca.TipoDeObraDao;
import br.com.cdan.negocio.geral.OrigemDao;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;

public class ObraFabricaTest {
	private static ObraFabricaTest instance = null;

	public static synchronized ObraFabricaTest getInstance() {
		if (instance == null) {
			instance = new ObraFabricaTest();
		}
		return instance;
	}

	public Obra criaObra() {
		Obra a = new Obra();
		a.setAssuntoPalavraChave("teste");
		a.setClassificacaoCDD("teste");
		a.setClassificacaoCutter("teste");
		//
		/*
		 * Set<ClassificacaoLiteraria> classificacaoLiterarias = new
		 * LinkedHashSet<>();
		 * classificacaoLiterarias.add(ClassificacaoLiterariaFabricaTest.
		 * getInstance().criaClassificacaoLiteraria());
		 * classificacaoLiterarias.add(ClassificacaoLiterariaFabricaTest.
		 * getInstance().criaClassificacaoLiteraria());
		 * a.setClassificacoesLiterarias(classificacaoLiterarias);
		 */
		//
		a.setEditora(EditoraFabricaTest.getInstance().criaEditora());
		// Criando exemplares
		Exemplar e1 = ExemplarFabricaTest.getInstance().criaExemplar();
		Exemplar e2 = ExemplarFabricaTest.getInstance().criaExemplar();
		Set<Exemplar> exemplares = new LinkedHashSet<>();
		exemplares.add(e1);
		exemplares.add(e2);
		a.setExemplares(exemplares);
		//
		a.setIdioma(IdiomaFabricaTest.getInstance().criaIdioma());
		a.setNivel(NivelFabricaTest.getInstance().criaNivel());
		a.setNome("Teste " + Math.random() * 1000);
		a.setObservacoes("teste");
		a.setOrigem(OrigemFabricaTest.getInstance().criaOrigem());
		a.setSerieColecaoLiteral(SerieColecaoLiteralFabricaTest.getInstance().criaSerieColecaoLiteral());
		a.setSetor(SetorFabricaTest.getInstance().criaSetor());
		a.setSubtitulo("");
		a.setTipoDeObra(TipoDeObraFabricaTest.getInstance().criaTipoDeObra());
		a.setCompartilhado(Boolean.TRUE);
		a.setAtivo(Boolean.TRUE);
		return a;
	}

	public Obra criaObraPersistido(EntityManager em) {
		ObraDao dao = new ObraDao(em);
		Obra a = criaObra();
		// Gravando primeiramente os objetos
		Set<ClassificacaoLiteraria> classificacoesLiterarias = new LinkedHashSet<>();
		ClassificacaoLiterariaDao classificacaoLiterariaDao = new ClassificacaoLiterariaDao(em);
		a.getClassificacoesLiterarias().forEach(classificacaoLiteraria -> {
			classificacaoLiterariaDao.persist(classificacaoLiteraria);
			classificacoesLiterarias.add(classificacaoLiteraria);
		});
		a.setClassificacoesLiterarias(classificacoesLiterarias);
		// Exemplar
		Set<Exemplar> exemplares = new LinkedHashSet<>();
		ExemplarDao daoExemplar = new ExemplarDao(em);
		a.getExemplares().forEach(e -> {
			daoExemplar.persist(e);
			exemplares.add(e);
		});
		a.setExemplares(exemplares);
		// Gravando Idioma
		IdiomaDao daoIdioma = new IdiomaDao(em);
		Idioma idioma = a.getIdioma();
		daoIdioma.persist(idioma);
		a.setIdioma(idioma);
		// Gravando Nível
		NivelDao daoNivel = new NivelDao(em);
		Nivel nivel = a.getNivel();
		daoNivel.persist(nivel);
		a.setNivel(nivel);
		// Gravando Origem
		OrigemDao daoOrigem = new OrigemDao(em);
		Origem origem = a.getOrigem();
		daoOrigem.persist(origem);
		a.setOrigem(origem);
		// Gravando SerieColecaoLiteral
		SerieColecaoLiteralDao daoSerieColecaoLiteral = new SerieColecaoLiteralDao(em);
		SerieColecaoLiteral serieColecaoLiteral = a.getSerieColecaoLiteral();
		daoSerieColecaoLiteral.persist(serieColecaoLiteral);
		a.setSerieColecaoLiteral(serieColecaoLiteral);
		// Gravando Setor
		SetorDao daoSetor = new SetorDao(em);
		Setor setor = a.getSetor();
		daoSetor.persist(setor);
		a.setSetor(setor);
		// Gravando Tipo de Obra
		TipoDeObraDao daoTipoDeObra = new TipoDeObraDao(em);
		TipoDeObra tipoDeObra = a.getTipoDeObra();
		daoTipoDeObra.persist(tipoDeObra);
		a.setTipoDeObra(tipoDeObra);

		// Gravando obra completa
		dao.persist(a);
		return a;
	}
}