package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;
import br.com.cdan.negocio.biblioteca.MatriculaDao;
import br.com.cdan.negocio.biblioteca.TipoDeContratoDao;

public class TipoDeContratoFabricaTest {
	private static TipoDeContratoFabricaTest instance = null;

	public static synchronized TipoDeContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeContratoFabricaTest();
		}
		return instance;
	}

	public TipoDeContrato criaTipoDeContrato() {
		TipoDeContrato t = new TipoDeContrato();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatricula());
		t.setMatriculas(matriculas);
		return t;
	}

	public TipoDeContrato criaTipoDeContratoPersistido(EntityManager em) {
		TipoDeContrato t = criaTipoDeContrato();
		TipoDeContratoDao dao = new TipoDeContratoDao(em);
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		MatriculaDao matriculaDao = new MatriculaDao(em);
		t.getMatriculas().forEach(matricula -> {
			matriculaDao.persist(matricula);
			matriculas.add(matricula);
		});
		t.setMatriculas(matriculas);
		//
		dao.persist(t);
		return t;
	}
}
