package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Bairro;
import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoUF;
import br.com.cdan.model.pedagogico.contrato.Transferencia;
import br.com.cdan.negocio.biblioteca.BairroDao;
import br.com.cdan.negocio.biblioteca.CidadeDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;
import br.com.cdan.negocio.biblioteca.EstadoUFDao;
import br.com.cdan.negocio.biblioteca.TransferenciaDao;

public class CidadeFabricaTest {
	private static CidadeFabricaTest instance = null;

	public static synchronized CidadeFabricaTest getInstance() {
		if (instance == null) {
			instance = new CidadeFabricaTest();
		}
		return instance;
	}

	public Cidade criaCidade() {
		Cidade cidade = new Cidade();
		cidade.setAtivo(Boolean.TRUE);
		cidade.setDescricao("teste");
		// Bairros
		Set<Bairro> bairros = new LinkedHashSet<>();
		Bairro b1 = BairroFabricaTest.getInstance().criaBairro();
		Bairro b2 = BairroFabricaTest.getInstance().criaBairro();
		bairros.add(b1);
		bairros.add(b2);
		cidade.setBairros(bairros);

		cidade.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUF());
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		Endereco e1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco e2 = EnderecoFabricaTest.getInstance().criaEndereco();
		enderecos.add(e1);
		enderecos.add(e2);
		cidade.setEnderecos(enderecos);
		// Transferências
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		transferencias.add(TransferenciaFabricaTest.getInstance().criaTransferencia());
		cidade.setTransferencias(transferencias);
		return cidade;
	}

	public Cidade criaCidadePersistido(EntityManager em) {
		CidadeDao dao = new CidadeDao(em);
		Cidade cidade = criaCidade();
		// Estado UF
		EstadoUFDao estadoUFDao = new EstadoUFDao(em);
		EstadoUF estadoUF = cidade.getEstadoUF();
		estadoUFDao.persist(estadoUF);
		cidade.setEstadoUF(estadoUF);
		// Bairros
		Set<Bairro> bairros = new LinkedHashSet<>();
		BairroDao daoBairro = new BairroDao(em);
		cidade.getBairros().forEach(b -> {
			daoBairro.persist(b);
			bairros.add(b);
		});
		cidade.setBairros(bairros);
		// Endereco
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao daoEndereco = new EnderecoDao(em);
		cidade.getEnderecos().forEach(e -> {
			daoEndereco.persist(e);
			enderecos.add(e);
		});
		cidade.setEnderecos(enderecos);

		// Transferências
		Set<Transferencia> transferencias = new LinkedHashSet<>();
		TransferenciaDao daoTransferencia = new TransferenciaDao(em);
		cidade.getTransferencias().forEach(b -> {
			daoTransferencia.persist(b);
			transferencias.add(b);
		});
		cidade.setTransferencias(transferencias);
		// Gravando cidade
		dao.persist(cidade);
		return cidade;
	}

}
