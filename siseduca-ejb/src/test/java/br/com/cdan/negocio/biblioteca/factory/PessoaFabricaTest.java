package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumSexo;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoCivil;
import br.com.cdan.model.pessoa.Aluno;
import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.model.pessoa.CarteiraHabilitacao;
import br.com.cdan.model.pessoa.Certidao;
import br.com.cdan.model.pessoa.Classificacao;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;
import br.com.cdan.negocio.biblioteca.AlunoDao;
import br.com.cdan.negocio.biblioteca.AnexoDocumentosDao;
import br.com.cdan.negocio.biblioteca.CarteiraHabilitacaoDao;
import br.com.cdan.negocio.biblioteca.CertidaoDao;
import br.com.cdan.negocio.biblioteca.CidadeDao;
import br.com.cdan.negocio.biblioteca.ClassificacaoDao;
import br.com.cdan.negocio.biblioteca.EmailDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;
import br.com.cdan.negocio.biblioteca.EstadoCivilDao;
import br.com.cdan.negocio.biblioteca.FuncionarioDao;
import br.com.cdan.negocio.biblioteca.PessoaDao;
import br.com.cdan.negocio.biblioteca.ResponsavelDao;

public class PessoaFabricaTest {
	private static PessoaFabricaTest instance = null;

	public static synchronized PessoaFabricaTest getInstance() {
		if (instance == null) {
			instance = new PessoaFabricaTest();
		}
		return instance;
	}

	public Pessoa criaPessoa() {
		Pessoa pessoa = new Pessoa();
		pessoa.setAluno(AlunoFabricaTest.getInstance().criaAluno());
		// Documentos
		Set<AnexoDocumentos> anexoDocumentos = new LinkedHashSet<>();
		anexoDocumentos.add(AnexoDocumentosFabricaTest.getInstance().criaAnexoDocumentos());
		pessoa.setAnexoDocumentos(anexoDocumentos);
		//
		pessoa.setAtivo(Boolean.TRUE);
		pessoa.setCarteiraHabilitacao(CarteiraHabilitacaoFabricaTest.getInstance().criaCarteiraHabilitacao());
		pessoa.setCertidao(CertidaoFabricaTest.getInstance().criaCertidao());
		pessoa.setCidadeNatal(CidadeFabricaTest.getInstance().criaCidade());
		pessoa.setClassificacao(ClassificacaoFabricaTest.getInstance().criaClassificacao());
		pessoa.setCpf("teste " + Math.random() * 10000);
		pessoa.setDataEmissaoTituloEleitor(Calendar.getInstance());
		pessoa.setDataExpedicaoRg(Calendar.getInstance());
		pessoa.setDataNascimento(Calendar.getInstance());
		pessoa.setDocumentoMilitar("teste " + Math.random() * 10000);
		// Emails
		Set<Email> emails = new LinkedHashSet<>();
		emails.add(EmailFabricaTest.getInstance().criaEmail());
		emails.add(EmailFabricaTest.getInstance().criaEmail());
		pessoa.setEmail(emails);
		//
		pessoa.setEmpresaLocalDeTrabalho("teste");
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		pessoa.setEndereco(enderecos);
		//
		pessoa.setEstadoCivil(EstadoCivilFabricaTest.getInstance().criaEstadoCivil());
		// Funcionarios
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		funcionarios.add(FuncionarioFabricaTest.getInstance().criaFuncionario());
		funcionarios.add(FuncionarioFabricaTest.getInstance().criaFuncionario());
		pessoa.setFuncionarios(funcionarios);
		//
		pessoa.setNome("teste" + Math.random() * 10000);
		pessoa.setNumeroDocumentoMilitar("teste");
		pessoa.setNumeroPassaporte("teste");
		pessoa.setOrgaoExpedidorRg("teste");
		pessoa.setPermitirEmprestimoBiblioteca(Boolean.TRUE);
		pessoa.setProfissaoFormacao("teste");
		// Responsaveis
		Set<Responsavel> responsaveis = new LinkedHashSet<Responsavel>();
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		responsaveis.add(ResponsavelFabricaTest.getInstance().criaResponsavel());
		pessoa.setResponsavel(responsaveis);
		//
		pessoa.setRg("teste" + Math.random() * 10000);
		pessoa.setSecaoTituloEleitor("teste");
		pessoa.setSexo(EnumSexo.M);
		pessoa.setTituloEleitor("teste");
		pessoa.setZonaTituloEleitor("teste");
		return pessoa;
	}

	public Pessoa criaPessoaPersistido(EntityManager em) {
		Pessoa p = criaPessoa();
		PessoaDao dao = new PessoaDao(em);
		//
		AlunoDao alunoDao = new AlunoDao(em);
		Aluno aluno = p.getAluno();
		alunoDao.persist(aluno);
		p.setAluno(aluno);
		// Documentos
		Set<AnexoDocumentos> anexoDocumentos = new LinkedHashSet<>();
		AnexoDocumentosDao anexoDocumentosDao = new AnexoDocumentosDao(em);
		anexoDocumentosDao.persist(anexoDocumentos);
		p.setAnexoDocumentos(anexoDocumentos);
		//
		CarteiraHabilitacaoDao carteiraHabilitacaoDao = new CarteiraHabilitacaoDao(em);
		CarteiraHabilitacao carteiraHabilitacao = p.getCarteiraHabilitacao();
		carteiraHabilitacaoDao.persist(carteiraHabilitacao);
		p.setCarteiraHabilitacao(carteiraHabilitacao);
		//
		CertidaoDao certidaoDao = new CertidaoDao(em);
		Certidao certidao = p.getCertidao();
		certidaoDao.persist(certidao);
		p.setCertidao(certidao);
		//
		CidadeDao cidadeDao = new CidadeDao(em);
		Cidade cidadeNatal = p.getCidadeNatal();
		cidadeDao.persist(cidadeNatal);
		p.setCidadeNatal(cidadeNatal);
		//
		ClassificacaoDao classificacaoDao = new ClassificacaoDao(em);
		Classificacao classificacao = p.getClassificacao();
		classificacaoDao.persist(classificacao);
		p.setClassificacao(classificacao);
		// Emails
		Set<Email> emails = new LinkedHashSet<>();
		EmailDao emailDao = new EmailDao(em);
		p.getEmail().forEach(email -> {
			emailDao.persist(email);
			emails.add(email);
		});
		;
		p.setEmail(emails);
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao enderecoDao = new EnderecoDao(em);
		p.getEndereco().forEach(endereco -> {
			enderecoDao.persist(endereco);
			enderecos.add(endereco);
		});
		;
		p.setEndereco(enderecos);
		//
		EstadoCivilDao estadoCivilDao = new EstadoCivilDao(em);
		EstadoCivil estadoCivil = p.getEstadoCivil();
		estadoCivilDao.persist(estadoCivil);
		p.setEstadoCivil(estadoCivil);
		// Funcionarios
		Set<Funcionario> funcionarios = new LinkedHashSet<>();
		FuncionarioDao funcionarioDao = new FuncionarioDao(em);
		p.getFuncionarios().forEach(funcionario -> {
			funcionarioDao.persist(funcionario);
			funcionarios.add(funcionario);
		});
		p.setFuncionarios(funcionarios);
		// Responsaveis
		Set<Responsavel> responsaveis = new LinkedHashSet<Responsavel>();
		ResponsavelDao responsavelDao = new ResponsavelDao(em);
		p.getResponsavel().forEach(responsavel -> {
			responsavelDao.persist(responsavel);
			responsaveis.add(responsavel);
		});
		p.setResponsavel(responsaveis);
		//
		dao.persist(p);
		return p;
	}

}
