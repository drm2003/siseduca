package br.com.cdan.negocio.pedagogico.pessoa.factory;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.cdan.comum.EnumSexo;
import br.com.cdan.model.pessoa.Pessoa;
import br.com.cdan.negocio.geral.cep.factory.CidadeFabricaTest;
import br.com.cdan.negocio.geral.factory.EstadoCivilFabricaTest;
import br.com.cdan.negocio.pedagogico.pessoa.PessoaDao;

public class PessoaFabricaTest {
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
		pessoa.setCpf("teste " + Math.random() * 10000);
		pessoa.setDataEmissaoTituloEleitor(Calendar.getInstance());
		pessoa.setDataExpedicaoRg(Calendar.getInstance());
		pessoa.setDataNascimento(Calendar.getInstance());
		pessoa.setDocumentoMilitar("teste " + Math.random() * 10000);
		pessoa.setEmpresaLocalDeTrabalho("teste");
		//
		pessoa.setNome("teste" + Math.random() * 10000);
		pessoa.setNumeroDocumentoMilitar("teste");
		pessoa.setNumeroPassaporte("teste");
		pessoa.setOrgaoExpedidorRg("teste");
		pessoa.setPermitirEmprestimoBiblioteca(Boolean.TRUE);
		pessoa.setProfissaoFormacao("teste");
		pessoa.setRg("teste" + Math.random() * 10000);
		pessoa.setSecaoTituloEleitor("teste");
		pessoa.setSexo(EnumSexo.M);
		pessoa.setTituloEleitor("teste");
		pessoa.setZonaTituloEleitor("teste");
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
