package br.com.cdan.negocio.financeiro.factory;

import java.math.BigDecimal;

import javax.persistence.EntityManager;

import br.com.cdan.model.financeiro.TipoDePagamentoProfessorHorista;
import br.com.cdan.negocio.financeiro.TipoDePagamentoProfessorHoristaDao;

public class TipoDePagamentoProfessorHoristaFabricaTest {
	private static TipoDePagamentoProfessorHoristaFabricaTest instance = null;

	public static synchronized TipoDePagamentoProfessorHoristaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TipoDePagamentoProfessorHoristaFabricaTest();
		}
		return instance;
	}

	public TipoDePagamentoProfessorHorista criaTipoDePagamentoProfessorHorista() {
		TipoDePagamentoProfessorHorista t = new TipoDePagamentoProfessorHorista();
		t.setAtivo(Boolean.TRUE);
		t.setDescricao("descricao");
		t.setValor(BigDecimal.valueOf(100));
		return t;
	}

	public TipoDePagamentoProfessorHorista criaTipoDePagamentoProfessorHoristaPersistido(EntityManager em) {
		TipoDePagamentoProfessorHorista t = criaTipoDePagamentoProfessorHorista();
		TipoDePagamentoProfessorHoristaDao dao = new TipoDePagamentoProfessorHoristaDao(em);
		dao.persist(t);
		return t;
	}
}
