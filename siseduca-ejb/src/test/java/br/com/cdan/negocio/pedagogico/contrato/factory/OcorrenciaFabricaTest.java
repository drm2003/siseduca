package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumOcorrenciaAlunoTurma;
import br.com.cdan.model.pedagogico.TipoDeOcorrencia;
import br.com.cdan.model.pedagogico.contrato.Ocorrencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.negocio.pedagogico.TipoDeOcorrenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.OcorrenciaDao;
import br.com.cdan.negocio.pedagogico.curso.TurmaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.factory.TipoDeOcorrenciaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.factory.FuncionarioFabricaTest;

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

	public Ocorrencia criaOcorrenciaPersistido(EntityManager em) {
		Ocorrencia o = criaOcorrencia();
		OcorrenciaDao dao = new OcorrenciaDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = o.getAluno();
		alunoDao.persist(aluno);
		o.setAluno(aluno);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario funcionario = o.getFuncionario();
		funcionarioDao.persist(funcionario);
		funcionarioDao.persist(funcionario);
		o.setFuncionario(funcionario);
		//
		TipoDeOcorrenciaDao tipoDeOcorrenciaDao = new TipoDeOcorrenciaDao(em);
		TipoDeOcorrencia tipoDeOcorrencia = o.getTipoDeOcorrencia();
		tipoDeOcorrenciaDao.persist(tipoDeOcorrencia);
		o.setTipoDeOcorrencia(tipoDeOcorrencia);
		//
		TurmaDao turmaDao = new TurmaDao(em);
		Turma turma = o.getTurma();
		turmaDao.persist(turma);
		o.setTurma(turma);
		//
		dao.persist(o);
		return o;
	}
}
