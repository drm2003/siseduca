package br.com.cdan.negocio.biblioteca.factory;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.biblioteca.AproveitamentoDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;

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
		EnderecoDao dao = new EnderecoDao();
		//
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao(em);
		Aproveitamento aproveitamento = e.getAproveitamento();
		aproveitamentoDao.persist(aproveitamento);
		e.setAproveitamento(aproveitamento);
		//
		e.setAtivo(Boolean.TRUE);
		e.setBairro(BairroFabricaTest.getInstance().criaBairro());
		e.setCep(CEPFabricaTest.getInstance().criaCEP());
		e.setCidade(CidadeFabricaTest.getInstance().criaCidade());
		e.setComplemento("teste");
		e.setEditora(EditoraFabricaTest.getInstance().criaEditora());
		//
		dao.persist(e);
		return e;
	}
}
