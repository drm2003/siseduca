package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.geral.SituacaoDoContrato;
import br.com.cdan.negocio.biblioteca.MatriculaDao;

public class SituacaoDoContratoFabricaTest {
	private static SituacaoDoContratoFabricaTest instance = null;

	public static synchronized SituacaoDoContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new SituacaoDoContratoFabricaTest();
		}
		return instance;
	}

	public SituacaoDoContrato criaSituacaoDoContrato() {
		SituacaoDoContrato s = new SituacaoDoContrato();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		s.setMatriculas(matriculas);
		return s;
	}

	public SituacaoDoContrato criaSituacaoDoContratoPersistido(EntityManager em) {
		SituacaoDoContrato s = new SituacaoDoContrato();
		s.setAtivo(Boolean.TRUE);
		s.setDescricao("descricao");
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		MatriculaDao matriculaDao = new MatriculaDao(em);
		s.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		s.setMatriculas(matriculas);
		//
		return s;
	}
}
