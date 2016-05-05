package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumTurnoPretendido;
import br.com.cdan.model.geral.SituacaoDoAluno;
import br.com.cdan.model.pessoa.AlunoInteressado;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Interessado;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.AlunoInteressadoDao;
import br.com.cdan.negocio.biblioteca.FollowDao;
import br.com.cdan.negocio.biblioteca.InteressadoDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;
import br.com.cdan.negocio.biblioteca.ResponsavelDao;
import br.com.cdan.negocio.biblioteca.SituacaoDoAlunoDao;

public class AlunoInteressadoFabricaTest {
	private static AlunoInteressadoFabricaTest instance = null;

	public static synchronized AlunoInteressadoFabricaTest getInstance() {
		if (instance == null) {
			instance = new AlunoInteressadoFabricaTest();
		}
		return instance;
	}

	public AlunoInteressado criaAlunoInteressado() {
		AlunoInteressado a = new AlunoInteressado();
		a.setAtivo(Boolean.TRUE);
		//
		Set<Follow> follows = new LinkedHashSet<>();
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		a.setFollows(follows);
		//
		a.setInteressado(InteressadoFabricaTest.getInstance().criaInteressado());
		a.setObservacoes("observacoes");
		a.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		a.setReceberEmail(Boolean.TRUE);
		a.setReceberSMS(Boolean.TRUE);
		//
		Set<Responsavel> responsaveis = new LinkedHashSet<>();
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		a.setResponsaveis(responsaveis);
		//
		a.setSituacaoDoAluno(SituacaoDoAlunoFabricaTest.getInstance().criaSituacaoDoAluno());
		a.setTurnoPretendido(EnumTurnoPretendido.MANHA);
		return a;
	}

	public AlunoInteressado criaAlunoInteressadoPersistido(EntityManager em) {
		AlunoInteressado a = criaAlunoInteressado();
		AlunoInteressadoDao dao = new AlunoInteressadoDao(em);
		//
		Set<Follow> follows = new LinkedHashSet<>();
		FollowDao followDao = new FollowDao(em);
		a.getFollows().forEach(follow -> {
			followDao.persist(follow);
			follows.add(follow);
		});
		a.setFollows(follows);
		//
		InteressadoDao interessadoDao = new InteressadoDao(em);
		Interessado interessado = a.getInteressado();
		interessadoDao.persist(interessado);
		a.setInteressado(interessado);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = a.getPessoa();
		pessoaDao.persist(pessoa);
		a.setPessoa(pessoa);
		//
		Set<Responsavel> responsaveis = new LinkedHashSet<>();
		ResponsavelDao responsavelDao = new ResponsavelDao(em);
		a.getResponsaveis().forEach(responsavel -> {
			responsavelDao.persist(responsavel);
			responsaveis.add(responsavel);
		});
		a.setResponsaveis(responsaveis);
		//
		SituacaoDoAlunoDao situacaoDoAlunoDao = new SituacaoDoAlunoDao(em);
		SituacaoDoAluno situacaoDoAluno = a.getSituacaoDoAluno();
		situacaoDoAlunoDao.persist(situacaoDoAluno);
		a.setSituacaoDoAluno(situacaoDoAluno);
		//
		dao.persist(a);
		return a;
	}
}
