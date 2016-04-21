package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;

import br.com.cdan.comum.EnumOcorrenciaAlunoTurma;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;

public class OcorrenciaFabricaTest {
	private static OcorrenciaFabricaTest instance = null;

	public static synchronized OcorrenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new OcorrenciaFabricaTest();
		}
		return instance;
	}

	public Ocorrencia criaOcorrencia() {
		Ocorrencia o = new Ocorrencia();
		o.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		o.setAtivo(Boolean.TRUE);
		o.setCurso("curso");
		o.setData(Calendar.getInstance());
		o.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionario());
		o.setOcorrenciaAlunoTurma(EnumOcorrenciaAlunoTurma.ALUNO);
		o.setPeriodo("periodo");
		o.setTipoDeOcorrencia(TipoDeOcorrenciaFabricaTest.getInstance().criaTipoDeOcorrencia());
		o.setTurma(TurmaFabricaTest.getInstance().criaTurma());
		return o;
	}

}
