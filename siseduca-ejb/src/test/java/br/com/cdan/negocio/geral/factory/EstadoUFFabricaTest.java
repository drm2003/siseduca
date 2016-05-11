package br.com.cdan.negocio.geral.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Cidade;
import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoUF;
import br.com.cdan.model.geral.Pais;
import br.com.cdan.negocio.geral.CidadeDao;
import br.com.cdan.negocio.geral.EnderecoDao;
import br.com.cdan.negocio.geral.EstadoUFDao;
import br.com.cdan.negocio.geral.PaisDao;

public class EstadoUFFabricaTest {
	private static EstadoUFFabricaTest instance = null;

	public static EstadoUFFabricaTest getInstance() {
		if (instance == null) {
			instance = new EstadoUFFabricaTest();
		}
		return instance;
	}

	public EstadoUF criaEstadoUF() {
		EstadoUF estadoUF = new EstadoUF();
		estadoUF.setAtivo(Boolean.TRUE);
		//
		Set<Cidade> cidades = new LinkedHashSet<>();
		cidades.add(CidadeFabricaTest.getInstance().criaCidade());
		cidades.add(CidadeFabricaTest.getInstance().criaCidade());
		estadoUF.setCidades(cidades);
		//
		estadoUF.setDescricao("teste");
		//
		Set<Endereco> enderecos = new LinkedHashSet<>();
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		enderecos.add(EnderecoFabricaTest.getInstance().criaEndereco());
		estadoUF.setEnderecos(enderecos);
		//
		estadoUF.setPais(PaisFabricaTest.getInstance().criaPais());
		return estadoUF;
	}

	public EstadoUF criaEstadoUFPersistido(EntityManager em) {
		EstadoUF e = criaEstadoUF();
		EstadoUFDao dao = new EstadoUFDao(em);
		//
		Set<Cidade> cidades = new LinkedHashSet<>();
		CidadeDao cidadeDao = new CidadeDao(em);
		e.getCidades().forEach(cidade -> {
			cidadeDao.persist(cidade);
			cidades.add(cidade);
		});
		e.setCidades(cidades);
		//
		EnderecoDao enderecoDao = new EnderecoDao(em);
		Set<Endereco> enderecos = new LinkedHashSet<>();
		e.getEnderecos().forEach(endereco -> {
			enderecoDao.persist(endereco);
			enderecos.add(endereco);
		});
		e.setEnderecos(enderecos);
		//
		Pais pais = e.getPais();
		PaisDao paisDao = new PaisDao(em);
		paisDao.persist(pais);
		e.setPais(pais);
		//
		dao.persist(e);
		return e;
	}
}
