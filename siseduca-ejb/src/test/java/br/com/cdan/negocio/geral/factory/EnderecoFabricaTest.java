package br.com.cdan.negocio.geral.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.biblioteca.Editora;
import br.com.cdan.model.geral.Bairro;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.biblioteca.EditoraDao;
import br.com.cdan.negocio.biblioteca.factory.EditoraFabricaTest;
import br.com.cdan.negocio.geral.BairroDao;
import br.com.cdan.negocio.geral.CidadeDao;
import br.com.cdan.negocio.geral.EnderecoDao;
import br.com.cdan.negocio.geral.cep.CEPDao;
import br.com.cdan.negocio.geral.cep.factory.CEPFabricaTest;
import br.com.cdan.negocio.pedagogico.contrato.AproveitamentoDao;
import br.com.cdan.negocio.pedagogico.contrato.factory.AproveitamentoFabricaTest;

public class EnderecoFabricaTest {
	private static EnderecoFabricaTest instance = null;

	public static synchronized EnderecoFabricaTest getInstance() {
		if (instance == null) {
			instance = new EnderecoFabricaTest();
		}
		return instance;
	}

	public Endereco criaEndereco() {
		Endereco e = new Endereco();
		e.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		e.setAtivo(Boolean.TRUE);
		e.setBairro(BairroFabricaTest.getInstance().criaBairro());
		e.setCep(CEPFabricaTest.getInstance().criaCEP());
		e.setCidade(CidadeFabricaTest.getInstance().criaCidade());
		e.setComplemento("teste");
		e.setEditora(EditoraFabricaTest.getInstance().criaEditora());

		return e;
	}

	public Endereco criaEnderecoPersistido(EntityManager em) {
		Endereco e = criaEndereco();
		EnderecoDao dao = new EnderecoDao(em);
		//
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		Aproveitamento aproveitamento = e.getAproveitamento();
		aproveitamentoDao.persist(aproveitamento);
		e.setAproveitamento(aproveitamento);
		//
		BairroDao bairroDao = new BairroDao(em);
		Bairro bairro = e.getBairro();
		bairroDao.persist(bairro);
		e.setBairro(bairro);
		//
		CEPDao cepDao = new CEPDao(em);
		CEP cep = e.getCep();
		cepDao.persist(cep);
		e.setCep(cep);
		//
		CidadeDao cidadeDao = new CidadeDao(em);
		Cidade cidade = e.getCidade();
		cidadeDao.persist(cidade);
		e.setCidade(cidade);
		//
		EditoraDao editoraDao = new EditoraDao(em);
		Editora editora = e.getEditora();
		editoraDao.persist(editora);
		e.setEditora(editora);
		//
		dao.persist(e);
		return e;
	}
}
