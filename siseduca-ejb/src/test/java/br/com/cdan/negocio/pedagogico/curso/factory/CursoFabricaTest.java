package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.negocio.acesso.factory.UsuarioFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.CursoDao;
import br.com.cdan.negocio.pedagogico.factory.SeriePadraoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.TipoDeCursoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.InteressadoFabricaTest;

public class CursoFabricaTest {
	private static CursoFabricaTest instance = null;

	public static synchronized CursoFabricaTest getInstance() {
		if (instance == null) {
			instance = new CursoFabricaTest();
		}
		return instance;
	}

	public Curso criaCurso(EntityManager em) {
		Curso c = new Curso();
		c.setAtivo(Boolean.TRUE);
		c.setAtoOficial("atoOficial");
		c.setCompartilhado(Boolean.TRUE);
		c.setCoordenador(UsuarioFabricaTest.getInstance().criaUsuarioPersistido(em));
		c.setCurriculoDoCurso("curriculoDoCurso");
		c.setIdadeMinima(Long.valueOf("10"));
		c.setInteressado(InteressadoFabricaTest.getInstance().criaInteressadoPersistido(em));
		c.setModular(Boolean.TRUE);
		c.setNome("nome");
		c.setNomeInstituicao("nomeInstituicao");
		c.setNumeroDeModulos("numeroDeModulos");
		c.setNumeroDeVagas("numeroDeVagas");
		c.setNumeroMaximoDeDependencias(Long.valueOf("2"));
		c.setPerfilDoCurso("perfilDoCurso");
		//
		c.setPontoDeEquilibrio("pontoDeEquilibrio");
		c.setReconhecimentoDoCurso("reconhecimentoDoCurso");
		//
		c.setSerieEquivalente(SeriePadraoFabricaTest.getInstance().criaSeriePadraoPersistido(em));
		c.setSigla("sigla");
		c.setTempoMaximoParaConclusaoDoCurso(Long.valueOf("10"));
		c.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCursoPersistido(em));
		//
		return c;
	}

	public Curso criaCursoPersistido(EntityManager em) {
		Curso c = criaCurso(em);
		CursoDao dao = new CursoDao(em);
		//
		dao.persist(c);
		return c;
	}
}
