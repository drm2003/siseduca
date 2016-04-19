package br.com.cdan.negocio.biblioteca.factory;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;

import br.com.cdan.model.geral.Endereco;
import br.com.cdan.model.geral.EstadoUF;
import br.com.cdan.model.geral.cep.CEP;
import br.com.cdan.model.geral.cep.EnumChaveCidade;
import br.com.cdan.model.geral.cep.EnumTipoCidade;
import br.com.cdan.model.pedagogico.contrato.Aproveitamento;
import br.com.cdan.negocio.biblioteca.AproveitamentoDao;
import br.com.cdan.negocio.biblioteca.CEPDao;
import br.com.cdan.negocio.biblioteca.EnderecoDao;
import br.com.cdan.negocio.biblioteca.EstadoUFDao;

public class CEPFabricaTest {
	private static CEPFabricaTest instance = null;

	public static synchronized CEPFabricaTest getInstance() {
		if (instance == null) {
			instance = new CEPFabricaTest();
		}
		return instance;
	}

	public CEP criaCEP() {
		CEP cep = new CEP();
		cep.setAproveitamento(AproveitamentoFabricaTest.getInstance().criaAproveitamento());
		cep.setAtivo(Boolean.TRUE);
		cep.setCepMax("teste");
		cep.setCepmin("teste");
		cep.setChave(EnumChaveCidade.C);
		cep.setCod_cidade("teste");
		cep.setCodCidadeSub("teste");
		cep.setCodMunicicio("teste");
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		Endereco e1 = EnderecoFabricaTest.getInstance().criaEndereco();
		Endereco e2 = EnderecoFabricaTest.getInstance().criaEndereco();
		enderecos.add(e1);
		enderecos.add(e2);
		cep.setEnderecos(enderecos);
		//
		cep.setTipoCidade(EnumTipoCidade.D);
		cep.setEstadoUF(EstadoUFFabricaTest.getInstance().criaEstadoUF());
		return cep;
	}

	public CEP criaCepPersistido(EntityManager em) {
		CEP cep = criaCEP();
		CEPDao cepDao = new CEPDao();
		cepDao.setEntityManager(em);
		// Aproveitamento
		AproveitamentoDao aproveitamentoDao = new AproveitamentoDao();
		Aproveitamento aproveitamento = cep.getAproveitamento();
		aproveitamentoDao.setEntityManager(em);
		aproveitamentoDao.persist(aproveitamento);
		// Endereços
		Set<Endereco> enderecos = new LinkedHashSet<>();
		EnderecoDao enderecoDao = new EnderecoDao();
		enderecoDao.setEntityManager(em);
		cep.getEnderecos().forEach(e -> {
			enderecoDao.persist(e);
			enderecos.add(e);
		});
		cep.setEnderecos(enderecos);
		// Estado UF
		EstadoUFDao estadoUFDao = new EstadoUFDao();
		estadoUFDao.persist(em);
		EstadoUF estadoUF = cep.getEstadoUF();
		estadoUFDao.persist(estadoUF);
		cep.setEstadoUF(estadoUF);
		//
		return cep;

	}

}
