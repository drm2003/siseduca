package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumOcorrenciaAlunoTurma;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.OcorrenciaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.TipoDeOcorrenciaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

public class OcorrenciaFabricaTest extends FabricaTest {
	private static OcorrenciaFabricaTest instance = null;

	public static synchronized OcorrenciaFabricaTest getInstance() {
		if (instance == null) {
			instance = new OcorrenciaFabricaTest();
		}
		return instance;
	}

	public Ocorrencia criaOcorrencia(EntityManager em) {
		Ocorrencia o = new Ocorrencia();
		o.setAluno(AlunoFabricaTest.getInstance().criaAlunoPersistido(em));
		o.setAtivo(Boolean.TRUE);
		o.setCurso("curso");
		o.setData(Calendar.getInstance());
		o.setFuncionario(FuncionarioFabricaTest.getInstance().criaFuncionarioPersistido(em));
		o.setOcorrenciaAlunoTurma(EnumOcorrenciaAlunoTurma.ALUNO);
		o.setPeriodo("periodo");
		o.setTipoDeOcorrencia(TipoDeOcorrenciaFabricaTest.getInstance().criaTipoDeOcorrenciaPersistido(em));
		o.setTurma(TurmaFabricaTest.getInstance().criaTurmaPersistido(em));
		o.setDescricao(criarStringDinamicaPorTamanho(50));
		return o;
	}

	public Ocorrencia criaOcorrenciaPersistido(EntityManager em) {
		Ocorrencia o = criaOcorrencia(em);
		OcorrenciaDao dao = new OcorrenciaDao(em);
		dao.persist(o);
		return o;
	}
}
