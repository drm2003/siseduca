package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class AproveitamentoFabricaTest extends FabricaTest {
	private static AproveitamentoFabricaTest instance = null;

	public static synchronized AproveitamentoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AproveitamentoFabricaTest();
		}
		return instance;
	}

	public Aproveitamento criaAproveitamento(EntityManager em) {
		Aproveitamento a = new Aproveitamento();
		a.setAtivo(Boolean.TRUE);
		a.setConfirmaCredito(Boolean.TRUE);
		a.setCoordenador(FuncionarioFabricaTest.getInstance().criaFuncionario(em));
		a.setEmenta(criarStringDinamicaPorTamanho(100));
		a.setNomeCurso(criarStringDinamicaPorTamanho(100));
		a.setNomeDisciplina(criarStringDinamicaPorTamanho(100));
		a.setSiglaEstabelecimento(criarStringDinamicaPorTamanho(10));
		a.setCargaHoraria(Calendar.getInstance().getTime());
		a.setCepEstabelecimento(CEPFabricaTest.getInstance().criaCepPersistido(em));
		a.setConfirmaCredito(Boolean.TRUE);
		a.setNecessarioExameProficiencia(Boolean.FALSE);
		a.setParecerCoordenador(criarStringDinamicaPorTamanho(100));
		return a;
	}

	public Aproveitamento criaAproveitamentoPersistido(EntityManager em) {
		Aproveitamento aproveitamento = criaAproveitamento(em);
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		aproveitamentoDao.persist(aproveitamento);
		return aproveitamento;
	}

}
