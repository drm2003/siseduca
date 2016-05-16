package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoEstagioMonografia;
import br.com.cdan.model.pedagogico.contrato.EstagioMonografia;
import br.com.cdan.negocio.pedagogico.contrato.EstagioMonografiaDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class EstagioMonografiaFabricaTest {
	private static EstagioMonografiaFabricaTest instance = null;

	public static synchronized EstagioMonografiaFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstagioMonografiaFabricaTest();
		}
		return instance;
	}

	public EstagioMonografia criaEstagioMonografia(EntityManager em) {
		EstagioMonografia e = new EstagioMonografia();
		e.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		//
		e.setAtivo(Boolean.TRUE);
		e.setConcluido(Boolean.FALSE);
		e.setDadosEmpresaConcedente(
				DadosEmpresaConcedenteFabricaTest.getInstance().criaDadosEmpresaConcedentePersistido(em));
		e.setDataInicio(Calendar.getInstance());
		e.setDataTermino(Calendar.getInstance());
		e.setHoraInicio(Calendar.getInstance().getTime().getTime());
		e.setHoraTermino(Calendar.getInstance().getTime().getTime());
		e.setNota(BigDecimal.TEN);
		e.setObservacao("observacao");
		e.setOrientadorSupervisor(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		e.setResultado("resultado");
		e.setTipoEstagioMonografia(EnumTipoEstagioMonografia.ESTAGIO);
		e.setTituloTema("tituloTema");
		return e;
	}

	public EstagioMonografia criaEstagioMonografiaPersistido(EntityManager em) {
		EstagioMonografia e = criaEstagioMonografia(em);
		EstagioMonografiaDao dao = new EstagioMonografiaDao(em);
		dao.persist(e);
		return e;
	}

}
