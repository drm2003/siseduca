package br.com.cdan.negocio.pedagogico.curso.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.LinhaProgramatica;
import br.com.cdan.negocio.pedagogico.curso.LinhaProgramaticaDao;

public class LinhaProgramaticaFabricaTest {
	private static LinhaProgramaticaFabricaTest instance = null;

	public static synchronized LinhaProgramaticaFabricaTest getInstance() {
		if (instance == null) {
			instance = new LinhaProgramaticaFabricaTest();
		}
		return instance;
	}

	public LinhaProgramatica criaLinhaProgramatica(EntityManager em) {
		LinhaProgramatica l = new LinhaProgramatica();
		l.setAtivo(Boolean.TRUE);
		l.setAssunto("assunto");
		l.setAtivo(Boolean.TRUE);
		l.setAula(Long.valueOf("10"));
		l.setDisciplina_MatrizCurricular(
				Disciplina_MatrizCurricularFabricaTest.getInstance().criaDisciplina_MatrizCurricularPersistido(em));
		l.setSubunidade("subunidade");
		l.setUnidade("unidade");
		return l;
	}

	public LinhaProgramatica criaLinhaProgramaticaPersistido(EntityManager em) {
		LinhaProgramatica l = criaLinhaProgramatica(em);
		LinhaProgramaticaDao dao = new LinhaProgramaticaDao();
		//
		dao.persist(l);
		return l;
	}
}
