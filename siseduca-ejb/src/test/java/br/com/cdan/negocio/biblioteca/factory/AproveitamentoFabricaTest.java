package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import br.com.cdan.model.pedagogico.contrato.Aproveitamento;

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
		aproveitamento.setCargaHoraria(Calendar.getInstance());
		aproveitamento.setCepEstabelecimento(CEPFabricaTest.getInstance().criaCEP());
		return aproveitamento;
	}

}
