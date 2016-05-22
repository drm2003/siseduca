package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumSexo;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.comum.FabricaTest;
import br.com.cdan.negocio.geral.cep.factory.CidadeFabricaTest;
import br.com.cdan.negocio.geral.factory.EstadoCivilFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;

public class PessoaFabricaTest extends FabricaTest {
	private static PessoaFabricaTest instance = null;

	public static synchronized PessoaFabricaTest getInstance() {
		if (instance == null) {
			instance = new PessoaFabricaTest();
		}
		return instance;
	}

	public Pessoa criaPessoa(EntityManager em) {
		Pessoa pessoa = new Pessoa();
		pessoa.setAtivo(Boolean.TRUE);
		pessoa.setCarteiraHabilitacao(
				CarteiraHabilitacaoFabricaTest.getInstance().criaCarteiraHabilitacaoPersistido(em));
		pessoa.setCertidao(CertidaoFabricaTest.getInstance().criaCertidaoPersistido(em));
		pessoa.setCidadeNatal(CidadeFabricaTest.getInstance().criaCidadePersistido(em));
		pessoa.setEstadoCivil(EstadoCivilFabricaTest.getInstance().criaEstadoCivilPersistido(em));
		pessoa.setClassificacao(ClassificacaoFabricaTest.getInstance().criaClassificacaoPersistido(em));
		//
		pessoa.setCpf(criarStringDinamicaPorTamanho(10));
		pessoa.setDataEmissaoTituloEleitor(Calendar.getInstance());
		pessoa.setDataExpedicaoRg(Calendar.getInstance());
		pessoa.setDataNascimento(Calendar.getInstance());
		pessoa.setDocumentoMilitar(criarStringDinamicaPorTamanho(10));
		pessoa.setEmpresaLocalDeTrabalho(criarStringDinamicaPorTamanho(5));
		//
		pessoa.setNome(criarStringDinamicaPorTamanho(100));
		pessoa.setNumeroDocumentoMilitar(criarStringDinamicaPorTamanho(5));
		pessoa.setNumeroPassaporte(criarStringDinamicaPorTamanho(5));
		pessoa.setOrgaoExpedidorRg(criarStringDinamicaPorTamanho(5));
		pessoa.setPermitirEmprestimoBiblioteca(Boolean.TRUE);
		pessoa.setProfissaoFormacao(criarStringDinamicaPorTamanho(5));
		pessoa.setRg(criarStringDinamicaPorTamanho(10));
		pessoa.setSecaoTituloEleitor(criarStringDinamicaPorTamanho(5));
		pessoa.setSexo(EnumSexo.M);
		pessoa.setTituloEleitor(criarStringDinamicaPorTamanho(5));
		pessoa.setZonaTituloEleitor(criarStringDinamicaPorTamanho(5));
		return pessoa;
	}

	public Pessoa criaPessoaPersistido(EntityManager em) {
		Pessoa p = criaPessoa(em);
		PessoaDao dao = new PessoaDao(em);
		//
		dao.persist(p);
		return p;
	}

}
