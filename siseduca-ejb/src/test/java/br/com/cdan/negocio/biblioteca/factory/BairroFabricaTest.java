package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Bairro;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.negocio.biblioteca.BairroDao;
import br.com.cdan.negocio.biblioteca.CidadeDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;

public class BairroFabricaTest {
	private static BairroFabricaTest instance = null;

	public static BairroFabricaTest getInstance() {
		if (instance == null) {
			instance = new BairroFabricaTest();
		}
		return instance;
	}

	public Bairro criaBairro() {
		Bairro bairro = new Bairro();
		bairro.setAtivo(Boolean.TRUE);
		bairro.setCidade(CidadeFabricaTest.getInstance().criaCidade());
		bairro.setDescricao("teste");
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		Endereco e1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco e2 = EnderecoFabricaTest.getInstance().criaEndereco();
		enderecos.add(e1);
		enderecos.add(e2);
		bairro.setEnderecos(enderecos);
		return bairro;
	}

	public Bairro criaBairroPersistido(EntityManager em) {
		Bairro bairro = criaBairro();
		BairroDao dao = new BairroDao(em);
		// Gravando Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao daoEndereco = new EnderecoDao(em);
		bairro.getEnderecos().forEach(e -> {
			daoEndereco.persist(e);
			enderecos.add(e);
		});
		bairro.setEnderecos(enderecos);
		// Gravando cidade
		Cidade cidade = bairro.getCidade();
		CidadeDao cidadeDao = new CidadeDao(em);
		cidadeDao.persist(cidade);
		bairro.setCidade(cidade);
		//
		dao.persist(bairro);
		return bairro;
	}

}
