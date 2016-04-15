package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import br.com.cdan.comum.EnumTipoDeTransferencia;
import br.com.cdan.model.pedagogico.contrato.Transferencia;

public class TransferenciaFabricaTest {
	private static TransferenciaFabricaTest instance = null;

	public static synchronized TransferenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new TransferenciaFabricaTest();
		}
		return instance;
	}

	public Transferencia criaTransferencia() {
		Transferencia t = new Transferencia();
		t.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		t.setCidade(CidadeFabricaTest.getInstance().criaCidade());
		t.setCurso("curso");
		t.setData(Calendar.getInstance());
		t.setEstabelecimentoProcedencia("estabelecimentoProcedencia");
		t.setMotivoDeTransferencia(MotivoDeTransferenciaFabricaTest.getInstance().criaMotivoDeTransferencia());
		t.setTipoDeTransferencia(EnumTipoDeTransferencia.EXPEDIDA);
		t.setTurma(TurmaFabricaTest.getInstance().criaTurma());
		return t;
	}

	public Transferencia criaTransferenciaPersistido() {
		Transferencia t = criaTransferencia();
		return t;
	}
}
