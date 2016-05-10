package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.empresa.Empresa;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.Telefone;
import br.com.cdan.model.geral.TipoDeResponsavel;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.AlunoInteressado;
import br.com.cdan.model.pessoa.Follow;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.EmpresaDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;
import br.com.cdan.negocio.biblioteca.FollowDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;
import br.com.cdan.negocio.biblioteca.ResponsavelDao;
import br.com.cdan.negocio.biblioteca.TelefoneDao;
import br.com.cdan.negocio.biblioteca.TipoDeResponsavelDao;

public class ResponsavelFabricaTest {
	private static ResponsavelFabricaTest instance = null;

	public static synchronized ResponsavelFabricaTest getInstance() {
		if (instance == null) {
			instance = new ResponsavelFabricaTest();
		}
		return instance;
	}

	public Responsavel criaResponsavel() {
		Responsavel r = new Responsavel();
		r.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		r.setAlunoInteressado(AlunoInteressadoFabricaTest.getInstance().criaAlunoInteressado());
		r.setAtivo(Boolean.TRUE);
		r.setEmpresa(EmpresaFabricaTest.getInstance().criaEmpresa());
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		r.setEnderecos(enderecos);
		// Follows
		Set<Follow> follows = new LinkedHashSet<>();
		follows.add(FollowFabricaTest.getInstance().criaFollow());
		r.setFollows(follows);
		//
		r.setLoginPortal("loginPortal");
		r.setObservacoes("observacoes");
		r.setPessoa(PessoaFabricaTest.getInstance().criaPessoa());
		r.setSenhaPortal("senhaPortal");
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		telefones.add(TelefoneFabricaTest.getInstance().criaTelefone());
		r.setTelefones(telefones);
		//
		r.setTipoDeResponsavel(TipoDeResponsavelFabricaTest.getInstance().criaTipoDeResponsavel());
		return r;
	}

	public Responsavel criaResponsavelPersistido(EntityManager em) {
		Responsavel r = criaResponsavel();
		ResponsavelDao dao = new ResponsavelDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = r.getAluno();
		alunoDao.persist(aluno);
		r.setAluno(aluno);
		//
		AlunoInteressado alunoInteressado = r.getAlunoInteressado();
		alunoDao.persist(alunoInteressado);
		r.setAlunoInteressado(alunoInteressado);
		//
		EmpresaDao empresaDao = new EmpresaDao(em);
		Empresa empresa = r.getEmpresa();
		empresaDao.persist(empresa);
		r.setEmpresa(empresa);
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao enderecoDao = new EnderecoDao(em);
		r.getEnderecos().forEach(endereco -> {
			enderecoDao.persist(endereco);
			enderecos.add(endereco);
		});
		r.setEnderecos(enderecos);
		// Follows
		FollowDao followDao = new FollowDao(em);
		Set<Follow> follows = new LinkedHashSet<>();
		r.getFollows().forEach(follow -> {
			followDao.persist(follow);
			follows.add(follow);
		});
		r.setFollows(follows);
		//
		PessoaDao pessoaDao = new PessoaDao(em);
		Pessoa pessoa = r.getPessoa();
		pessoaDao.persist(pessoa);
		r.setPessoa(pessoa);
		// Telefones
		Set<Telefone> telefones = new LinkedHashSet<>();
		TelefoneDao telefoneDao = new TelefoneDao(em);
		r.getTelefones().forEach(telefone -> {
			telefoneDao.persist(telefone);
			telefones.add(telefone);
		});
		r.setTelefones(telefones);
		//
		TipoDeResponsavelDao tipoDeResponsavelDao = new TipoDeResponsavelDao(em);
		TipoDeResponsavel tipoDeResponsavel = r.getTipoDeResponsavel();
		tipoDeResponsavelDao.persist(tipoDeResponsavel);
		r.setTipoDeResponsavel(tipoDeResponsavel);
		//
		dao.persist(r);
		return r;
	}
}