package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Matricula;
import br.com.cdan.model.pedagogico.contrato.TipoDeContrato;
import br.com.cdan.negocio.pedagogico.contrato.TipoDeContratoDao;

public class TipoDeContratoFabricaTest {
	private static TipoDeContratoFabricaTest instance = null;

	public static synchronized TipoDeContratoFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDeContratoFabricaTest();
		}
		return instance;
	}

	public TipoDeContrato criaTipoDeContrato(EntityManager em) {
		TipoDeContrato t = new TipoDeContrato();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		//
		Set<Matricula> matriculas = new LinkedHashSet<>();
		matriculas.add(MatriculaFabricaTest.getInstance().criaMatriculaPersistido(em));
		t.setMatriculas(matriculas);
		return t;
	}

	public TipoDeContrato criaTipoDeContratoPersistido(EntityManager em) {
		TipoDeContrato t = criaTipoDeContrato(em);
		TipoDeContratoDao dao = new TipoDeContratoDao(em);
		//
		dao.persist(t);
		return t;
	}
}
