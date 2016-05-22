package br.com.cdan.negocio.pedagogico.curso.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.curso.Disciplina;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.curso.DisciplinaDao;
import br.com.cdan.negocio.pedagogico.factory.TipoDeCursoFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.TipoDeDisciplinaFabricaTest;

public class DisciplinaFabricaTest extends FabricaTest {
	private static DisciplinaFabricaTest instance = null;

	public static synchronized DisciplinaFabricaTest getInstance() {
		if (instance == null) {
			instance = new DisciplinaFabricaTest();
		}
		return instance;
	}

	public Disciplina criaDisciplina(EntityManager em) {
		Disciplina d = new Disciplina();
		d.setAtivo(Boolean.TRUE);
		d.setCodigoINEP(criarStringDinamicaPorTamanho(10));
		d.setCompartilhado(Boolean.TRUE);
		d.setDisciplinaDependente(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricularPersistido(em));
		//
		d.setMatrizCurricular(MatrizCurricularFabricaTest.getInstance().criaMatrizCurricularPersistido(em));
		d.setNome(criarStringDinamicaPorTamanho(100));
		d.setSigla(criarStringDinamicaPorTamanho(10));
		d.setTipoDeCurso(TipoDeCursoFabricaTest.getInstance().criaTipoDeCursoPersistido(em));
		d.setTipoDeDisciplina(TipoDeDisciplinaFabricaTest.getInstance().criaTipoDeDisciplinaPersistido(em));
		//
		d.setValorHoraAula(BigDecimal.TEN);
		return d;
	}

	public Disciplina criaDisciplinaPersistido(EntityManager em) {
		Disciplina d = criaDisciplina(em);
		DisciplinaDao dao = new DisciplinaDao(em);
		//
		dao.persist(d);
		return d;
	}

}
