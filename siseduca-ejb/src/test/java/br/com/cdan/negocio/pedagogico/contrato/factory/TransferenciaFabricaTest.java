package br.com.cdan.negocio.pedagogico.contrato.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTipoDeTransferencia;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.pedagogico.contrato.MotivoDeTransferencia;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.model.pedagogico.curso.Turma;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.negocio.geral.CidadeDao;
import br.com.cdan.negocio.geral.factory.CidadeFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeTransferenciaDao;
import br.com.cdan.negocio.pedagogico.contrato.TransferenciaDao;
import br.com.cdan.negocio.pedagogico.curso.TurmaDao;
import br.com.cdan.negocio.pedagogico.curso.factory.TurmaFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.factory.AlunoFabricaTest;

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

	public Transferencia criaTransferenciaPersistido(EntityManager em) {
		Transferencia t = criaTransferencia();
		TransferenciaDao dao = new TransferenciaDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = t.getAluno();
		alunoDao.persist(aluno);
		t.setAluno(aluno);
		//
		CidadeDao cidadeDao = new CidadeDao(em);
		Cidade cidade = t.getCidade();
		cidadeDao.persist(cidade);
		t.setCidade(cidade);
		//
		MotivoDeTransferenciaDao motivoDeTransferenciaDao = new MotivoDeTransferenciaDao(em);
		MotivoDeTransferencia motivoDeTransferencia = t.getMotivoDeTransferencia();
		motivoDeTransferenciaDao.persist(motivoDeTransferencia);
		t.setMotivoDeTransferencia(motivoDeTransferencia);
		//
		TurmaDao turmaDao = new TurmaDao(em);
		Turma turma = t.getTurma();
		turmaDao.persist(turma);
		t.setTurma(turma);
		//
		dao.persist(t);
		return t;
	}
}
