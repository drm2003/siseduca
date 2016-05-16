package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.MatrizCurricular;
import br.com.cdan.negocio.pedagogico.curso.MatrizCurricularDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeCursoFabricaTest;

public class MatrizCurricularFabricaTest {
	private static MatrizCurricularFabricaTest instance = null;

	public static synchronized MatrizCurricularFabricaTest getInstance() {
		if (instance == null) {
			instance = new MatrizCurricularFabricaTest();
		}
		return instance;
	}

	public MatrizCurricular criaMatrizCurricular(EntityManager em) {
		MatrizCurricular m = new MatrizCurricular();
		m.setAtivo(Boolean.TRUE);
		//
		m.setNome("nome");
		m.setQuantidadeModulo(Long.valueOf("3"));
		m.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCursoPersistido(em));
		//
		m.setUtilizaDisciplinasSequenciais(Boolean.TRUE);
		m.setUtilizarSimuladoParaComporMedia(Boolean.TRUE);
		return m;
	}

	public MatrizCurricular criaMatrizCurricularPersistido(EntityManager em) {
		MatrizCurricular m = criaMatrizCurricular(em);
		MatrizCurricularDao dao = new MatrizCurricularDao(em);
		dao.persist(m);
		return m;
	}

}
