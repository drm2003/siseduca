package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.biblioteca.AproveitamentoDao;
import br.com.cdan.negocio.biblioteca.CEPDao;

public class AproveitamentoFabricaTest {
	private static AproveitamentoFabricaTest instance = null;

	public static synchronized AproveitamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AproveitamentoFabricaTest();
		}
		return instance;
	}

	public Aproveitamento criaAproveitamento() {
		Aproveitamento aproveitamento = new Aproveitamento();
		aproveitamento.setAtivo(Boolean.TRUE);
		aproveitamento.setCargaHoraria(Calendar.getInstance().getTime());
		aproveitamento.setCepEstabelecimento(CEPFabricaTest.getInstance().criaCEP());
		return aproveitamento;
	}

	public Aproveitamento criaAproveitamento(EntityManager em) {
		Aproveitamento aproveitamento = criaAproveitamento();
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		// Cep
		CEPDao cepDao = new CEPDao(em);
		CEP cepEstabelecimento = aproveitamento.getCepEstabelecimento();
		cepDao.persist(cepEstabelecimento);
		aproveitamento.setCepEstabelecimento(cepEstabelecimento);
		//
		aproveitamentoDao.persist(aproveitamento);
		return aproveitamento;
	}

}
