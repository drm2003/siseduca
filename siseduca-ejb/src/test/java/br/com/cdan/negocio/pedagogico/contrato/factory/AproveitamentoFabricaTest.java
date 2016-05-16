package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;

public class AproveitamentoFabricaTest {
	private static AproveitamentoFabricaTest instance = null;

	public static synchronized AproveitamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AproveitamentoFabricaTest();
		}
		return instance;
	}

	public Aproveitamento criaAproveitamento(EntityManager em) {
		Aproveitamento aproveitamento = new Aproveitamento();
		aproveitamento.setAtivo(Boolean.TRUE);
		aproveitamento.setCargaHoraria(Calendar.getInstance().getTime());
		aproveitamento.setCepEstabelecimento(CEPFabricaTest.getInstance().criaCepPersistido(em));
		return aproveitamento;
	}

	public Aproveitamento criaAproveitamentoPersistido(EntityManager em) {
		Aproveitamento aproveitamento = criaAproveitamento(em);
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		aproveitamentoDao.persist(aproveitamento);
		return aproveitamento;
	}

}
