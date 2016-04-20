package br.com.cdan.negocio.biblioteca.factory;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import br.com.cdan.comum.EnumSexo;
import br.com.cdan.model.geral.Email;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.pessoa.AnexoDocumentos;
import br.com.cdan.model.pessoa.Funcionario;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.model.pessoa.Responsavel;

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

}
