package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.contato.MelhorHorarioParaContato;
import br.com.cdan.model.contato.Midia;
import br.com.cdan.model.contato.TipoDeContato;
import br.com.cdan.model.contrato.MotivoDeNaoFechamentoDeContrato;
import br.com.cdan.model.geral.Origem;
import br.com.cdan.model.pedagogico.curso.Curso;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.model.pessoa.ObservacaoDoAluno;
import br.com.cdan.negocio.contato.MelhorHorarioParaContatoDao;
import br.com.cdan.negocio.contato.MidiaDao;
import br.com.cdan.negocio.contato.TipoDeContatoDao;
import br.com.cdan.negocio.contato.factory.MelhorHorarioParaContatoFabricaTest;
import br.com.cdan.negocio.contato.factory.MidiaFabricaFabricaTest;
import br.com.cdan.negocio.contato.factory.TipoDeContatoFabricaTest;
import br.com.cdan.negocio.geral.OrigemDao;
import br.com.cdan.negocio.geral.factory.OrigemFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.MotivoDeNaoFechamentoDeContratoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.MotivoDeNaoFechamentoDeContratoFabricaTest;
import br.com.cdan.negocio.pedagogico.curso.CursoDao;
import br.com.cdan.negocio.pedagogico.curso.factory.CursoFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.AlunoDao;
import br.com.cdan.negocio.pedagogico.pessoa.FollowDao;
import br.com.cdan.negocio.pedagogico.pessoa.FuncionarioDao;
import br.com.cdan.negocio.pedagogico.pessoa.InteressadoDao;
import br.com.cdan.negocio.pedagogico.pessoa.ObservacaoDoAlunoDao;

public class InteressadoFabricaTest {
	private static InteressadoFabricaTest instance = null;

	public static synchronized InteressadoFabricaTest getInstance() {
		if (instance == null) {
			instance = new InteressadoFabricaTest();
		}
		return instance;
	}

	public Interessado criaInteressado() {
		Interessado i = new Interessado();
		i.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		i.setAtendente(FuncionarioFabricaTest.getInstance().criaFuncionario());
		i.setAtivo(Boolean.TRUE);
		// Cursos e Turmas de Interesse
		Set<Curso> cursos = new LinkedHashSet<>();
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		cursos.add(CursoFabricaTest.getInstance().criaCurso());
		i.setCursosTumasDeInteresse(cursos);
		// Follows do interessado
		Set<Follow> followsDoInteressado = new LinkedHashSet<>();
		followsDoInteressado.add(FollowFabricaTest.getInstance().criaFollow());
		followsDoInteressado.add(FollowFabricaTest.getInstance().criaFollow());
		i.setFollowsDoInteressado(followsDoInteressado);
		//
		i.setMelhorHorarioParaContato(MelhorHorarioParaContatoFabricaTest.getInstance().criaMelhorHorarioParaContato());
		i.setMidia(MidiaFabricaFabricaTest.getInstance().criaMidia());
		i.setMotivoDeNaoFechamentoDeContrato(
				MotivoDeNaoFechamentoDeContratoFabricaTest.getInstance().criaMotivosDeNaoFechamentoDeContrato());
		i.setObservacaoDoAluno(ObservacaoDoAlunoFabricaTest.getInstance().criaObservacaoDoAluno());
		i.setOrigem(OrigemFabricaTest.getInstance().criaOrigem());
		i.setOutraEscolaDoAluno("outraEscolaDoAluno");
		i.setPromotorDeVendas(FuncionarioFabricaTest.getInstance().criaFuncionario());
		i.setTipoDeContato(TipoDeContatoFabricaTest.getInstance().criaTipoDeContato());
		return i;
	}

	public Interessado criaInteressadoPersistido(EntityManager em) {
		Interessado i = criaInteressado();
		InteressadoDao dao = new InteressadoDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = i.getAluno();
		alunoDao.persist(aluno);
		i.setAluno(aluno);
		//
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		Funcionario atendente = i.getAtendente();
		funcionarioDao.persist(atendente);
		i.setAtendente(atendente);
		// Cursos e Turmas de Interesse
		Set<Curso> cursos = new LinkedHashSet<>();
		CursoDao cursoDao = new CursoDao(em);
		i.getCursosTumasDeInteresse().forEach(cursoTurmaDeInteresse -> {
			cursoDao.persist(cursoTurmaDeInteresse);
			cursos.add(cursoTurmaDeInteresse);
		});
		i.setCursosTumasDeInteresse(cursos);
		// Follows do interessado
		Set<Follow> followsDoInteressado = new LinkedHashSet<>();
		FollowDao followDao = new FollowDao(em);
		i.getFollowsDoInteressado().forEach(followDoInteressado -> {
			followDao.persist(followsDoInteressado);
			followsDoInteressado.add(followDoInteressado);
		});
		i.setFollowsDoInteressado(followsDoInteressado);
		//
		MelhorHorarioParaContatoDao melhorHorarioParaContatoDao = new MelhorHorarioParaContatoDao(em);
		MelhorHorarioParaContato melhorHorarioParaContato = i.getMelhorHorarioParaContato();
		melhorHorarioParaContatoDao.persist(melhorHorarioParaContato);
		i.setMelhorHorarioParaContato(melhorHorarioParaContato);
		//
		MidiaDao midiaDao = new MidiaDao(em);
		Midia midia = i.getMidia();
		midiaDao.persist(midia);
		i.setMidia(midia);
		//
		MotivoDeNaoFechamentoDeContratoDao motivoDeNaoFechamentoDeContratoDao = new MotivoDeNaoFechamentoDeContratoDao(
				em);
		MotivoDeNaoFechamentoDeContrato motivoDeNaoFechamentoDeContrato = i.getMotivoDeNaoFechamentoDeContrato();
		motivoDeNaoFechamentoDeContratoDao.persist(motivoDeNaoFechamentoDeContrato);
		i.setMotivoDeNaoFechamentoDeContrato(
				MotivoDeNaoFechamentoDeContratoFabricaTest.getInstance().criaMotivosDeNaoFechamentoDeContrato());
		//
		ObservacaoDoAlunoDao observacaoDoAlunoDao = new ObservacaoDoAlunoDao(em);
		ObservacaoDoAluno observacaoDoAluno = i.getObservacaoDoAluno();
		observacaoDoAlunoDao.persist(observacaoDoAluno);
		i.setObservacaoDoAluno(observacaoDoAluno);
		//
		OrigemDao origemDao = new OrigemDao(em);
		Origem origem = i.getOrigem();
		origemDao.persist(origem);
		i.setOrigem(origem);
		//
		Funcionario promotorDeVendas = i.getPromotorDeVendas();
		funcionarioDao.persist(promotorDeVendas);
		i.setPromotorDeVendas(promotorDeVendas);
		//
		TipoDeContatoDao tipoDeContatoDao = new TipoDeContatoDao(em);
		TipoDeContato tipoDeContato = i.getTipoDeContato();
		tipoDeContatoDao.persist(tipoDeContato);
		i.setTipoDeContato(tipoDeContato);
		//
		dao.persist(i);
		return i;
	}
}
