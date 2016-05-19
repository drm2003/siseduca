package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeTransferencia;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.negocio.geral.cep.factory.CidadeFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.TransferenciaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

public class TransferenciaFabricaTest {
	private static TransferenciaFabricaTest instance = null;

	public static synchronized TransferenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TransferenciaFabricaTest();
		}
		return instance;
	}

	public Transferencia criaTransferencia(EntityManager em) {
		Transferencia t = new Transferencia();
		t.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		t.setCidade(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		t.setCurso("curso");
		t.setData(Calendar.getInstance());
		t.setEstabelecimentoProcedencia("estabelecimentoProcedencia");
		t.setMotivoDeTransferencia(
				MotivoDeTransferenciaFabricaTest.getInstance().criaMotivoDeTransferenciaPersistido(em));
		t.setTipoDeTransferencia(EnumTipoDeTransferencia.EXPEDIDA);
		t.setTurma(TurmaFabricaTest.getInstance().criaTurmaPersistido(em));
		return t;
	}

	public Transferencia criaTransferenciaPersistido(EntityManager em) {
		Transferencia t = criaTransferencia(em);
		TransferenciaDao dao = new TransferenciaDao(em);
		//
		dao.persist(t);
		return t;
	}
}
